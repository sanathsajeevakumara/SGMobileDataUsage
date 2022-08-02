package com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model

import androidx.room.Entity

@Entity(primaryKeys = ["year", "quarter"])
data class QuarterConsumption(
//    @PrimaryKey val id: Int? = null,
    val year: Int,
    val quarter: String,
    val volume: Float
)