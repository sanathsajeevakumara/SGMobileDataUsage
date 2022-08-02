package com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.remote.response_model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("resource_id") val resourceId: String?,
    val records: List<Record>
)
