package com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.yearlyTotalConsumption.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.usecase.YearlyTotalConsumptionUseCase
import com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.yearlyTotalConsumption.state.YearlyConsumptionState
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

//        Log.d("ThreadChecker",
//            "GetYearlyTotalConsumptionData running on ${Thread.currentThread().name}")

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

//        Log.d("ThreadChecker",
//            "FetchDataStoreSearchData running on ${Thread.currentThread().name}")

        yearlyTotalConsumptionUseCase.retrieveDataStoreConsumptionData()
    }
}