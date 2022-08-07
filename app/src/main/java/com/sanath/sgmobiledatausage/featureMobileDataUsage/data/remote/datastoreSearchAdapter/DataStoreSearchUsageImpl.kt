package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter

import com.sanath.sgmobiledatausage.featureMobileDataUsage.core.util.SGConstValue
import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.SGMobileDataUsageAPI
import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter.model.DataStoreSearchResponse
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