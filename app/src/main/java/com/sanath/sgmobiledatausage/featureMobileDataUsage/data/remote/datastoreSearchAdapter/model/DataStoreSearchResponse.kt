package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.datastoreSearchAdapter.model

import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.responseModel.Record

data class DataStoreSearchResponse(
    val isCallSuccess: Boolean,
    val dataStoreConsumptionRecord: List<Record>?,
    val message: String = ""
)
