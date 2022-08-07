package com.sanath.sgmobiledatausage.featureMobileDataUsage.di

import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter.DataStoreSearchUsageImpl
import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter.DataStoreSearchUsageInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreSearchInterfaceModule {

    @Singleton
    @Binds
    abstract fun bindDataStoreSearchInterface(
        dataStoreSearchUsageImpl: DataStoreSearchUsageImpl
    ): DataStoreSearchUsageInterface
}