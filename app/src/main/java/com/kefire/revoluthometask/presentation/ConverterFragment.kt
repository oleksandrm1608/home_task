package com.kefire.revoluthometask.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kefire.revoluthometask.R
import com.kefire.revoluthometask.domain.error.ServerConnectionError
import com.kefire.revoluthometask.domain.error.ServerError
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_converter.*
import javax.inject.Inject

class ConverterFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ConverterViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ConverterViewModel::class.java)
    }

    private val adapter: CurrencyAdapter by lazy {
        CurrencyAdapter(rated_recycler_view.layoutManager!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.startFetchingRates()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rated_recycler_view.adapter = adapter
        viewModel.ratesLiveData.observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccess) {
                error_text_view.visibility = View.GONE
                adapter.setRates(result.getOrNull()!!)
            } else {
                error_text_view.visibility = View.VISIBLE
                when (result.exceptionOrNull()!!) {
                    is ServerConnectionError -> error_text_view.text =
                        getString(R.string.server_connection_error)
                    is ServerError -> error_text_view.text = getString(R.string.server_error)
                }
            }
        })
    }

    companion object {
        fun newInstance() = ConverterFragment()
    }
}