package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.QuarterConsumption

@Database(
    entities = [QuarterConsumption::class],
    version = 2
)
abstract class MobileDataUsageDataBase: RoomDatabase() {
    abstract val mobileDataUsageDao: MobileDataUsageDao
}