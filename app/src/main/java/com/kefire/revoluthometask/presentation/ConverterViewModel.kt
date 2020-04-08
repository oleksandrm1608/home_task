package com.kefire.revoluthometask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kefire.revoluthometask.domain.entity.Currency
import com.kefire.revoluthometask.domain.error.ServerConnectionError
import com.kefire.revoluthometask.domain.error.ServerError
import com.kefire.revoluthometask.domain.repository.RateRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConverterViewModel @Inject constructor(
    private val rateRepository: RateRepository
) : ViewModel() {

    private val _ratesLiveData = MutableLiveData<Result<List<Currency>>>()
    val ratesLiveData: LiveData<Result<List<Currency>>> = _ratesLiveData

    fun startFetchingRates() {
        viewModelScope.launch {
            while (true) {
                launch {
                    try {
                        val rates = rateRepository.getRates()
                        _ratesLiveData.value = Result.success(rates)
                    } catch (error: ServerConnectionError) {
                        _ratesLiveData.value = Result.failure(error)
                    } catch (error: ServerError) {
                        _ratesLiveData.value = Result.failure(error)
                    }
                }
                delay(1000L)
            }
        }
    }
}