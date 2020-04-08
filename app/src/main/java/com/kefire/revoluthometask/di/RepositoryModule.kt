package com.kefire.revoluthometask.di

import com.kefire.revoluthometask.data.server.RateRepositoryImpl
import com.kefire.revoluthometask.domain.repository.RateRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideRateRepository(rateRepository: RateRepositoryImpl): RateRepository
}