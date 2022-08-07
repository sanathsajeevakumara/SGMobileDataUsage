package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.FilteredQuarterConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.QuarterConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.YearlyTotalConsumption
import kotlinx.coroutines.flow.Flow

@Dao
interface MobileDataUsageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMobileDataUsageRecord(usage: List<QuarterConsumption>)

    @Query("SELECT * FROM quarterconsumption")
    fun getAllQuarterConsumption(): Flow<List<QuarterConsumption>>

    @Query("SELECT year,SUM(volume) as totalConsumption From quarterconsumption GROUP BY year")
    fun getYearlyTotalConsumption(): Flow<List<YearlyTotalConsumption>>

    @Query("SELECT year, group_concat(volume) as volumes , group_concat(quarter) as quarters FROM quarterconsumption GROUP BY year")
    suspend fun getFilteredQuarterConsumption():List<FilteredQuarterConsumption>

    @Query("SELECT year from quarterconsumption ORDER BY year ASC LIMIT 1")
    suspend fun getInitialYear():Int

}