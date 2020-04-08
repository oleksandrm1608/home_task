package com.kefire.revoluthometask.di

import androidx.lifecycle.ViewModel
import com.kefire.revoluthometask.presentation.ConverterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ConverterViewModel::class)
    fun provideConverterViewModel(converterViewModel: ConverterViewModel): ViewModel
}