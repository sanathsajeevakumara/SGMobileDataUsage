package com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter

import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter.model.DataStoreSearchResponse
import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.response_model.Record
import org.junit.Assert.*

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