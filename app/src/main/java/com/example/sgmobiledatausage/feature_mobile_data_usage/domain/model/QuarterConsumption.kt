package com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuarterConsumption(
    @PrimaryKey val id: Int? = null,
    val year: Int,
    val quarter: String,
    val volume: Float
)
