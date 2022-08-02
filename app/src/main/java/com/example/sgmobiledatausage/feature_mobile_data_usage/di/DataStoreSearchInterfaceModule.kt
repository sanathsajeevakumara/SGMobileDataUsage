package com.example.sgmobiledatausage.feature_mobile_data_usage.di

import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter.DataStoreSearchUsageImpl
import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter.DataStoreSearchUsageInterface
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