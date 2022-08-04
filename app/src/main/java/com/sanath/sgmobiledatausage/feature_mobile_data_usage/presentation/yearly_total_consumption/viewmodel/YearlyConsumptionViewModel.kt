package com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.usecase.YearlyTotalConsumptionUseCase
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.state.YearlyConsumptionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearlyConsumptionViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val yearlyTotalConsumptionUseCase: YearlyTotalConsumptionUseCase
) : ViewModel() {

    var yearlyState by mutableStateOf<YearlyConsumptionState>(YearlyConsumptionState.OnEmptyScreen)

    fun getYearlyTotalConsumptionData() = viewModelScope.launch(dispatcher) {

            yearlyState = YearlyConsumptionState.OnLoading

            yearlyTotalConsumptionUseCase.getYearlyTotalConsumption()
                .catch { error ->
                    yearlyState = YearlyConsumptionState.OnError(error.toString())
                }
                .collect { yearlyTotalConsumption ->
                    yearlyState =
                        YearlyConsumptionState.OnYearlyConsumptionAvailable(yearlyTotalConsumption)
                }
        }

    fun fetchDataStoreSearchData() = viewModelScope.launch(dispatcher) {
            yearlyTotalConsumptionUseCase.retrieveDataStoreConsumptionData()
    }
}