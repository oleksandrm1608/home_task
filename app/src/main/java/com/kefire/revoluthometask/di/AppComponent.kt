package com.kefire.revoluthometask.di

import android.content.Context
import com.kefire.revoluthometask.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DaggerAndroidModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        RetrofitModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}