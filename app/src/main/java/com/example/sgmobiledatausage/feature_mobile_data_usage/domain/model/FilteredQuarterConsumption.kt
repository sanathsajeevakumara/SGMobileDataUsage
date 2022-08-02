package com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model

import java.lang.Exception

data class FilteredQuarterConsumption(
    val year: Int,
    val quarters: String,
    val volumes: String
) {

    fun getMappedQuarterUsageByYear(): Map<String, String>? {
        return try {
//            printZipValue()
            val quartersList:List<String> = quarters.split(",")
            val volumesList:List<String> = volumes.split(",")
            quartersList.zip(volumesList).toMap()

        }catch (e: Exception){
            null
        }
    }

    private fun printZipValue() {

        val quartersList:List<String> = quarters.split(",")
        val volumesList:List<String> = volumes.split(",")
        quartersList.zip(volumesList) { quarterList, volumeList->
            println(
                "MappedQuarterUsageByYear: ($quarterList, $volumeList)"
            )
        }
    }

}
