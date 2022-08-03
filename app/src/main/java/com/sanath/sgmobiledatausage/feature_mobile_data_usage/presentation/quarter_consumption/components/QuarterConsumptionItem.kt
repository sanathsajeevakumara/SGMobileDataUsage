package com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sanath.sgmobiledatausage.R
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.util.isLandScape
import com.google.accompanist.pager.*

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun QuarterConsumptionItem(
    pageNumber: Int,
    quarterConsumption: List<FilteredQuarterConsumption>,
    contentPadding: PaddingValues,
) {

    val pagerState = rememberPagerState()

    val cardHeightSize: Dp = if (isLandScape()) { 280.dp } else { 400.dp }
    val spacerHeightBeforeYear: Dp = if (isLandScape()) { 0.dp } else { 16.dp }
    val spacerHeightAfterYear: Dp = if (isLandScape()) { 16.dp } else { 64.dp }
    val indicatorPadding: Dp = if (isLandScape()) { 24.dp } else { 32.dp }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    HorizontalPager(
        count = quarterConsumption.size,
        state = pagerState,
        contentPadding = contentPadding
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeightSize)
                .padding(
                    horizontal = 16.dp,
                    vertical = 40.dp
                ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Spacer(modifier = Modifier.height(spacerHeightBeforeYear))

                Text(
                    text = stringResource(id = R.string.year) + " ${quarterConsumption[page].year}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(spacerHeightAfterYear))

                val quarterAndConsumptionMap = quarterConsumption[page].getMappedQuarterConsumptionByYear()

                quarterAndConsumptionMap?.let { quarterAndConsumption ->

                    if (quarterAndConsumption.isNotEmpty()) {
                        for (key in quarterAndConsumption.keys) {

                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "$key : ",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = quarterAndConsumption[key]?:"",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = indicatorPadding),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }

    LaunchedEffect(pagerState){
        pagerState.animateScrollToPage(pageNumber)
    }
    
}