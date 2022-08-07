package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter

import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter.model.DataStoreSearchResponse
import retrofit2.http.Query

interface DataStoreSearchUsageInterface {

    suspend fun getMobileDataUsage(
        @Query("resource_id") resourceId: String?,
    ): DataStoreSearchResponse

}