package com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.quarterConsumption.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.usecase.QuarterConsumptionUseCase
import com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.quarterConsumption.state.QuarterConsumptionState
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

    private var initialYear: Int = -1

    var quarterState by mutableStateOf<QuarterConsumptionState>(QuarterConsumptionState.OnScreenEmpty)

    private val quarterExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("QuarterUsageViewModel Exception $throwable")
    }

    fun getQuarterConsumptionsByYear(year: Int) = viewModelScope
        .launch(dispatcher + quarterExceptionHandler) {

            //retrieve the initial year
            if (initialYear == -1) {
                initialYear = quarterConsumptionUseCase.getInitialYear()
            }

            quarterState = QuarterConsumptionState.OnLoading

            val quarterConsumptionsByYear = quarterConsumptionUseCase.getQuarterConsumptionByYear()

            quarterState = if (quarterConsumptionsByYear.isNotEmpty()) {
                QuarterConsumptionState.OnQuarterUsageDataAvailable(
                    pageNumber = year - initialYear,
                    quarterUsage = quarterConsumptionsByYear
                )
            } else {
                QuarterConsumptionState.OnError(message = "Quarter consumption by year is not populate")
            }

        }
}