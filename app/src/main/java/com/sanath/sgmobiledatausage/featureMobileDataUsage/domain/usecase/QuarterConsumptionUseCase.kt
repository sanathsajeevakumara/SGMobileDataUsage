package com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.usecase

import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.FilteredQuarterConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.repository.MobileDataUsageRepository
import javax.inject.Inject

class QuarterConsumptionUseCase @Inject constructor(
    private val repository: MobileDataUsageRepository
) {

    suspend fun getQuarterConsumptionByYear(): List<FilteredQuarterConsumption> {
        return repository.getFilteredQuarterConsumption()
    }

    suspend fun getInitialYear(): Int {
        return repository.getInitialYear()
    }

}