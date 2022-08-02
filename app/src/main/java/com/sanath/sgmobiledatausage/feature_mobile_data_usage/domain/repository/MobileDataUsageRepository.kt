package com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.repository

import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.QuarterConsumption
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.YearlyTotalConsumption
import kotlinx.coroutines.flow.Flow

interface MobileDataUsageRepository {

    fun getYearlyTotalConsumption(): Flow<List<YearlyTotalConsumption>>

    suspend fun getInitialYear():Int

    suspend fun getFilteredQuarterConsumption():List<FilteredQuarterConsumption>

    suspend fun insertAllMobileDataUsageRecord(quarterConsumptionList: List<QuarterConsumption>)

}