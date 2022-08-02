package com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.response_model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Result(
    @SerializedName("resource_id") val resourceId: String?,
    val records: List<Record>
)
