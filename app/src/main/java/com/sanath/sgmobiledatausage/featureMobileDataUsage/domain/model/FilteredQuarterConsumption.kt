package com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model

import java.lang.Exception

data class FilteredQuarterConsumption(
    val year: Int,
    val quarters: String,
    val volumes: String
) {

    fun getMappedQuarterConsumptionByYear(): Map<String, String>? {
        return try {
            val quartersList:List<String> = quarters.split(",")
            val volumesList:List<String> = volumes.split(",")
            quartersList.zip(volumesList).toMap()

        }catch (e: Exception){
            null
        }
    }

}
