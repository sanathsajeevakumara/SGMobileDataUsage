package com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.state

import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.YearlyTotalConsumption

sealed class YearlyConsumptionState {
    object OnEmptyScreen : YearlyConsumptionState()
    object OnLoading : YearlyConsumptionState()
    data class OnYearlyUsageAvailable(val yearlyUsage: List<YearlyTotalConsumption>) :
        YearlyConsumptionState()
    data class OnError(val message: String) : YearlyConsumptionState()
}
