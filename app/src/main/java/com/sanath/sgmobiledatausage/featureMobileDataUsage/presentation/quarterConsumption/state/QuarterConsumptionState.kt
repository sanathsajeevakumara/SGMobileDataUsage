package com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.quarterConsumption.state

import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.FilteredQuarterConsumption

sealed interface QuarterConsumptionState {
    object OnScreenEmpty : QuarterConsumptionState
    object OnLoading : QuarterConsumptionState
    data class OnQuarterUsageDataAvailable(
        val pageNumber: Int,
        val quarterUsage: List<FilteredQuarterConsumption>
    ) : QuarterConsumptionState

    data class OnError(val message: String) : QuarterConsumptionState
}
