package com.sanath.sgmobiledatausage.featureMobileDataUsage.data.repository

import com.sanath.sgmobiledatausage.featureMobileDataUsage.data.local.MobileDataUsageDao
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.FilteredQuarterConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.QuarterConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.YearlyTotalConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.repository.MobileDataUsageRepository
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