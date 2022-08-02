package com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.repository

import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.local.MobileDataUsageDao
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.QuarterConsumption
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.YearlyTotalConsumption
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.repository.MobileDataUsageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MobileDataUsageRepositoryImpl @Inject constructor(
    private val dao: MobileDataUsageDao
): MobileDataUsageRepository {

    override fun getYearlyTotalConsumption(): Flow<List<YearlyTotalConsumption>> {
        return dao.getYearlyTotalConsumption()
    }

    override suspend fun getInitialYear(): Int {
        return dao.getInitialYear()
    }

    override suspend fun getFilteredQuarterConsumption(): List<FilteredQuarterConsumption> {
        return dao.getFilteredQuarterConsumption()
    }

    override suspend fun insertAllMobileDataUsageRecord(quarterConsumptionList: List<QuarterConsumption>) {
        return dao.insertAllMobileDataUsageRecord(quarterConsumptionList)
    }
}