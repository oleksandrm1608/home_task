package com.kefire.revoluthometask.di

import com.kefire.revoluthometask.presentation.ConverterActivity
import com.kefire.revoluthometask.presentation.ConverterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface DaggerAndroidModule {

    @ContributesAndroidInjector
    fun converterFragment(): ConverterFragment
}