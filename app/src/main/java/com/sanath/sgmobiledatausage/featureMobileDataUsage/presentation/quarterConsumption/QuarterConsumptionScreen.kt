package com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.quarterConsumption

import android.util.Log
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
import com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.quarterConsumption.components.QuarterConsumptionItem
import com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.quarterConsumption.state.QuarterConsumptionState
import com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.quarterConsumption.viewmodel.QuarterConsumptionViewModel
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

    when (val state = viewModel.quarterState) {
        is QuarterConsumptionState.OnScreenEmpty -> {

//            Log.d("ThreadChecker",
//                "QuarterConsumptionScreen running on ${Thread.currentThread().name}")

            viewModel.getQuarterConsumptionsByYear(year = year)
        }
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