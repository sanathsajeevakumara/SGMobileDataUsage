package com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote

import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.response_model.MobileDataUsageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SGMobileDataUsageAPI {

    @GET("/api/action/datastore_search")
    suspend fun getMobileDataUsage(
        @Query("resource_id") resourceId: String?
    ): Response<MobileDataUsageResponse>

}