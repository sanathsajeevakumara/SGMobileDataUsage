package com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.QuarterConsumption

@Database(
    entities = [QuarterConsumption::class],
    version = 2
)
abstract class MobileDataUsageDataBase: RoomDatabase() {
    abstract val mobileDataUsageDao: MobileDataUsageDao
}