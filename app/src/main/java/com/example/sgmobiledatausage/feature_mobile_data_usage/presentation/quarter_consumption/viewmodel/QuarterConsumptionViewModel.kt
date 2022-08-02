package com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.usecase.QuarterConsumptionUseCase
import com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.state.QuarterConsumptionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuarterConsumptionViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val quarterConsumptionUseCase: QuarterConsumptionUseCase
) : ViewModel() {

    private var _initialYear: Int = -1

    var quarterState by mutableStateOf<QuarterConsumptionState>(QuarterConsumptionState.OnScreenEmpty)
        private set

    private val _quarterExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("QuarterUsageViewModel Exception $throwable")
    }

    fun getQuarterConsumptionsByYear() = viewModelScope
        .launch(dispatcher + _quarterExceptionHandler) {

            //retrieve the initial year
            if (_initialYear == -1) {
                _initialYear = quarterConsumptionUseCase.getInitialYear()
            }

            quarterState = QuarterConsumptionState.OnLoading

            val quarterConsumptionsByYear = quarterConsumptionUseCase.getQuarterConsumptionByYear()

            quarterState = if (quarterConsumptionsByYear.isNotEmpty()) {
                QuarterConsumptionState.OnQuarterUsageDataAvailable(
                    pageNumber = _initialYear,
                    quarterUsage = quarterConsumptionsByYear
                )
            } else {
                QuarterConsumptionState.OnError(message = "Quarter consumption by year is not populate")
            }

        }
}