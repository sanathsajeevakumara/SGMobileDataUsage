package com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.usecase.YearlyTotalConsumptionUseCase
import com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.state.YearlyConsumptionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearlyConsumptionViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val yearlyTotalConsumptionUseCase: YearlyTotalConsumptionUseCase
): ViewModel() {

    var state by mutableStateOf<YearlyConsumptionState>(YearlyConsumptionState.OnEmptyScreen)
        private set

    fun getYearlyTotalConsumptionData() = viewModelScope
        .launch(dispatcher) {
            yearlyTotalConsumptionUseCase.getYearlyTotalConsumption()
                .catch { error ->
                    state = YearlyConsumptionState.OnError(error.toString())
                }
                .collect { yearlyTotalConsumption ->
                    state = YearlyConsumptionState.OnYearlyUsageAvailable(yearlyTotalConsumption)
                }
        }

    fun fetchDataStoreSearchData() = viewModelScope
        .launch(dispatcher) {
            yearlyTotalConsumptionUseCase.retrieveDataStoreConsumptionData()
        }
}