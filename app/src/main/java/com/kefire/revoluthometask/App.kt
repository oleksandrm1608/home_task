package com.kefire.revoluthometask

import com.kefire.revoluthometask.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App: DaggerApplication() {

    private val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}