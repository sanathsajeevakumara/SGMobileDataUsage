package com.example.sgmobiledatausage.feature_mobile_data_usage.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.QuarterConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.YearlyTotalConsumption
import kotlinx.coroutines.flow.Flow

@Dao
interface MobileDataUsageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDataUsageRecords(usage: List<QuarterConsumption>)

    @Query("SELECT * FROM quarterconsumption")
    fun getAllQuarterUsage(): Flow<List<QuarterConsumption>>

    @Query("SELECT year,SUM(volume) as totalUsage From quarterconsumption GROUP BY year")
    fun getYearlyUsage(): Flow<List<YearlyTotalConsumption>>

    @Query("SELECT year, group_concat(volume) as volumes , group_concat(quarter) as quarters FROM quarterconsumption GROUP BY year")
    suspend fun getGroupedQuarterUsage():List<FilteredQuarterConsumption>

    @Query("SELECT year from quarterconsumption ORDER BY year ASC LIMIT 1")
    suspend fun getInitialYear():Int

}