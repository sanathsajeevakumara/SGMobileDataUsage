package com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.yearlyTotalConsumption.state

import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.YearlyTotalConsumption

sealed interface YearlyConsumptionState {
    object OnEmptyScreen : YearlyConsumptionState
    object OnLoading : YearlyConsumptionState
    data class OnYearlyConsumptionAvailable(val yearlyConsumption: List<YearlyTotalConsumption>) :
        YearlyConsumptionState

    data class OnError(val message: String) : YearlyConsumptionState
}
