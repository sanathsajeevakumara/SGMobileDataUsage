package com.example.sgmobiledatausage.feature_mobile_data_usage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.QuarterConsumption

@Database(
    entities = [QuarterConsumption::class],
    version = 1
)
abstract class MobileDataUsageDataBase: RoomDatabase() {
    abstract val mobileDataUsageDao: MobileDataUsageDao
}