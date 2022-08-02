package com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sanath.sgmobiledatausage.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.components.QuarterConsumptionItem
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.state.QuarterConsumptionState
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.viewmodel.QuarterConsumptionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.annotation.Destination

@ExperimentalPagerApi
@ExperimentalMaterial3Api
@Composable
@Destination
fun QuarterConsumptionScreen(
    year: Int,
    viewModel: QuarterConsumptionViewModel = hiltViewModel()
) {

    when(val state = viewModel.quarterState) {
        is QuarterConsumptionState.OnScreenEmpty -> viewModel.getQuarterConsumptionsByYear(year = year)
        is QuarterConsumptionState.OnLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is QuarterConsumptionState.OnError -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.message)
            }
        }
        is QuarterConsumptionState.OnQuarterUsageDataAvailable -> {
            Scaffold(
                topBar = {
                    SmallTopAppBar(
                        title = {
                            Text(text = stringResource(R.string.quarter_consumption_title))
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
                QuarterConsumptionItem(
                    pageNumber = state.pageNumber,
                    quarterConsumption = state.quarterUsage,
                    contentPadding = values,
                )
            }
        }
    }

}