package com.example.sgmobiledatausage.feature_mobile_data_usage.di

import com.example.sgmobiledatausage.feature_mobile_data_usage.data.repository.MobileDataUsageRepositoryImpl
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.repository.MobileDataUsageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataUsageRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDataUsageRepository(
        dataUsageRepositoryImpl: MobileDataUsageRepositoryImpl
    ): MobileDataUsageRepository

}