package com.sanath.sgmobiledatausage.featureMobileDataUsage.di

import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.repository.MobileDataUsageRepositoryImpl
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.repository.MobileDataUsageRepository
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