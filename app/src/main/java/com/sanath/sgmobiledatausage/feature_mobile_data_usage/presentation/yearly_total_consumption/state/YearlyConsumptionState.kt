package com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.state

import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.YearlyTotalConsumption

sealed class YearlyConsumptionState {
    object OnEmptyScreen : YearlyConsumptionState()
    object OnLoading : YearlyConsumptionState()
    data class OnYearlyConsumptionAvailable(val yearlyConsumption: List<YearlyTotalConsumption>) :
        YearlyConsumptionState()
    data class OnError(val message: String) : YearlyConsumptionState()
}
