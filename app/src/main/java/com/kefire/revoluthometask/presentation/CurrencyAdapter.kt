package com.kefire.revoluthometask.presentation

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kefire.revoluthometask.R
import com.kefire.revoluthometask.domain.entity.Currency
import com.kefire.revoluthometask.domain.entity.Type
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency_rate.view.*

class CurrencyAdapter(
    private val layoutManager: RecyclerView.LayoutManager
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private var rates = mutableListOf<Currency>()
    private var topValue: Double? = 100.0

    fun setRates(newRates: List<Currency>) {
        if (rates.isEmpty()) {
            rates = newRates.toMutableList()
            notifyDataSetChanged()
        } else {
            rates.forEachIndexed { index, currency ->
                val newCurrency = newRates.find { it.type == currency.type }!!
                rates[index] = newCurrency
            }
            notifyItemRangeChanged(1, rates.size - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutInFlatter = LayoutInflater.from(parent.context)
        val view = layoutInFlatter.inflate(R.layout.item_currency_rate, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int = rates.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = rates[position]
        holder.bind(item)
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {

        private lateinit var item: Currency

        private val currencyValueListener = object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                topValue = try {
                    text.toString().toDouble()
                } catch (error: NumberFormatException) {
                    null
                }
                notifyItemRangeChanged(1, rates.size - 1)
            }
        }

        init {
            itemView.setOnClickListener {
                if (adapterPosition != 0) {
                    rates.removeAt(adapterPosition)
                    rates.add(0, item)
                    notifyItemMoved(adapterPosition, 0)
                    notifyItemRangeChanged(0, 2)

                    topValue = try {
                        itemView.currency_value_edit_text.text.toString().toDouble()
                    } catch (error: NumberFormatException) {
                        null
                    }
                    layoutManager.scrollToPosition(0)
                }
            }
        }

        fun bind(item: Currency) {
            this.item = item
            itemView.currency_name_text_view.text = item.name
            itemView.currency_description_text_view.text = item.description
            val flagIconResource = when (item.type) {
                Type.EUR -> R.drawable.ic_flag_eu
                Type.USD -> R.drawable.ic_flag_usa
                Type.SEK -> R.drawable.ic_flag_sweden
                Type.CAD -> R.drawable.ic_flag_canada
                else -> R.drawable.ic_flag_default
            }
            itemView.country_flag_image_view.setBackgroundResource(flagIconResource)

            val currencyValue: String = topValue?.let {
                "%.2f".format(it * item.rate / rates[0].rate)
            } ?: ""

            if (adapterPosition == 0) {
                itemView.currency_value_edit_text.isEnabled = true
                itemView.currency_value_edit_text.removeTextChangedListener(currencyValueListener)
                itemView.currency_value_edit_text.setText(currencyValue)
                itemView.currency_value_edit_text.addTextChangedListener(currencyValueListener)
                itemView.currency_value_edit_text.requestFocus()

            } else {
                itemView.currency_value_edit_text.isEnabled = false
                itemView.currency_value_edit_text.removeTextChangedListener(currencyValueListener)
                itemView.currency_value_edit_text.setText(currencyValue)
            }
        }

        override val containerView: View? = itemView
    }
}