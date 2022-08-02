package com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.state

import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption

sealed class QuarterConsumptionState {
    object OnScreenEmpty : QuarterConsumptionState()
    object OnLoading : QuarterConsumptionState()
    data class OnQuarterUsageDataAvailable(
        val pageNumber: Int,
        val quarterUsage: List<FilteredQuarterConsumption>
    ) : QuarterConsumptionState()

    data class OnError(val message: String) : QuarterConsumptionState()
}
