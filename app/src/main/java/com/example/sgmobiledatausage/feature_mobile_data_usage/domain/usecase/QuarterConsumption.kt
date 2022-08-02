package com.example.sgmobiledatausage.feature_mobile_data_usage.domain.usecase

import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.repository.MobileDataUsageRepository
import javax.inject.Inject

class QuarterConsumption @Inject constructor(
    private val repository: MobileDataUsageRepository
) {

    suspend fun getQuarterConsumptionByYear(): List<FilteredQuarterConsumption> {
        return repository.getFilteredQuarterConsumption()
    }

    suspend fun getInitialYear(): Int {
        return repository.getInitialYear()
    }

}