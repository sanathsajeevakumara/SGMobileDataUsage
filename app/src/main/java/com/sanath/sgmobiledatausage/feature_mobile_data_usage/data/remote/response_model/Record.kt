package com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.remote.response_model

import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("_id") val id: Int,
    @SerializedName("quarter") val quarter: String,
    @SerializedName("volume_of_mobile_data") val volumeOfMobileData: String,
) {
    fun getYearValue(): Int {
        return quarter.split("-")[0].toInt()
    }

    fun getQuarterValue(): String {
        return quarter.split("-")[1]
    }
}
