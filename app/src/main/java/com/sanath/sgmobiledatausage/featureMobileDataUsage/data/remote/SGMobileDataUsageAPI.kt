package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote

import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.responseModel.MobileDataUsageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SGMobileDataUsageAPI {

    @GET("/api/action/datastore_search")
    suspend fun getMobileDataUsage(
        @Query("resource_id") resourceId: String?
    ): Response<MobileDataUsageResponse>

}