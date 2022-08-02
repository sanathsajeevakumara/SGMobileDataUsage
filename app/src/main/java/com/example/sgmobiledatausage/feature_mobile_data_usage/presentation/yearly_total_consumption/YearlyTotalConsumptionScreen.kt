package com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sgmobiledatausage.R
import com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.components.YearlyConsumptionItem
import com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.state.YearlyConsumptionState
import com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.viewmodel.YearlyConsumptionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalPagerApi
@ExperimentalMaterial3Api
@Composable
@Destination(start = true)
fun YearlyUsageScreen(
    navigator: DestinationsNavigator,
    viewModel: YearlyConsumptionViewModel = hiltViewModel()
) {

    val viewModelState = viewModel.state

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(R.string.yearly_consumption_title))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.shadow(
                    elevation = 5.dp,
                    ambientColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { values ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            when (viewModelState) {

                is YearlyConsumptionState.OnEmptyScreen -> {
                    viewModel.fetchDataStoreSearchData()
                    viewModel.getYearlyTotalConsumptionData()
                }

                is YearlyConsumptionState.OnLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is YearlyConsumptionState.OnError -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = viewModelState.message)
                    }
                }

                is YearlyConsumptionState.OnYearlyUsageAvailable -> {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = values
                    ) {

                        items(viewModelState.yearlyUsage.size) { index ->
                            val yearlyConsumptionData = viewModelState.yearlyUsage[index]
                            YearlyConsumptionItem(
                                year = yearlyConsumptionData.year.toString(),
                                totalConsumption = yearlyConsumptionData.totalConsumption.toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
//                                        navigator.navigate(
//                                            QuarterUsageScreenDestination(
//                                                year = yearlyConsumptionData.year
//                                            )
//                                        )
                                    }
                                    .padding(16.dp)
                            )

                            if (index < viewModelState.yearlyUsage.size - 1) {
                                Divider(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp
                                    ),
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}