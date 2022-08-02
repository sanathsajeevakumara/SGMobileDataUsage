package com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter.model

import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.response_model.Record

data class DataStoreSearchResponse(
    val isCallSuccess: Boolean,
    val dataStoreConsumptionRecord: List<Record>?,
    val message: String = ""
)
