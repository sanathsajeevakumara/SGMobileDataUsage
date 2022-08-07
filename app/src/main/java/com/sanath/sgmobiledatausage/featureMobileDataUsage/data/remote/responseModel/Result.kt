package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.remote.responseModel

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("resource_id") val resourceId: String?,
    val records: List<Record>
)
