package com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter

import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter.model.DataStoreSearchResponse
import retrofit2.http.Query

interface DataStoreSearchUsage {
    suspend fun getMobileDataUsage(
        @Query("resource_id") resourceId: String,
    ): DataStoreSearchResponse
}