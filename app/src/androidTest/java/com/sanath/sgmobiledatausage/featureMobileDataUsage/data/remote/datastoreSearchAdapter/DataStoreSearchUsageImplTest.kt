package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter

import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter.model.DataStoreSearchResponse
import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.responseModel.Record

class DataStoreSearchUsageImplTest(
    private val mockData: List<Record>
): DataStoreSearchUsageInterface {

    override suspend fun getMobileDataUsage(resourceId: String?): DataStoreSearchResponse {
        return DataStoreSearchResponse(
            isCallSuccess = true,
            dataStoreConsumptionRecord = mockData,
            message = ""
        )
    }
}