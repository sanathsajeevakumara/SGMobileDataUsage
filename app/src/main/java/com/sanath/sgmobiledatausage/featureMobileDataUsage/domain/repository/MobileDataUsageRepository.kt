package com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.repository

import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.FilteredQuarterConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.QuarterConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.YearlyTotalConsumption
import kotlinx.coroutines.flow.Flow

interface MobileDataUsageRepository {

    fun getYearlyTotalConsumption(): Flow<List<YearlyTotalConsumption>>

    suspend fun getInitialYear():Int

    suspend fun getFilteredQuarterConsumption():List<FilteredQuarterConsumption>

    suspend fun insertAllMobileDataUsageRecord(quarterConsumptionList: List<QuarterConsumption>)

}