package com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter

import com.example.sgmobiledatausage.feature_mobile_data_usage.core.util.SGConstValue
import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.SGMobileDataUsageAPI
import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter.model.DataStoreSearchResponse
import javax.inject.Inject

class DataStoreSearchUsageImpl @Inject constructor(
    private val api: SGMobileDataUsageAPI
): DataStoreSearchUsageInterface {

    override suspend fun getMobileDataUsage(resourceId: String?): DataStoreSearchResponse {

        val mobileDataConsumptionRes = api.getMobileDataUsage(SGConstValue.RESOURCE_ID)

        return if (mobileDataConsumptionRes.isSuccessful) {
            DataStoreSearchResponse(
                isCallSuccess = true,
                dataStoreConsumptionRecord = mobileDataConsumptionRes.body()?.result?.records,
            )
        } else {
            DataStoreSearchResponse(
                isCallSuccess = false,
                dataStoreConsumptionRecord = null,
                message = mobileDataConsumptionRes.message()
            )
        }
    }

}