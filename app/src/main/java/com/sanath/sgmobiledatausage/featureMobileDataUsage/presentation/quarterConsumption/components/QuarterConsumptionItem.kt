package com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.quarterConsumption.components

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
import com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model.FilteredQuarterConsumption
import com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.util.isLandScape
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

    val cardHeightSize: Dp
    val spacerHeightBeforeYear: Dp
    val spacerHeightAfterYear: Dp
    val indicatorPadding: Dp

    if (isLandScape()) {
        cardHeightSize = 280.dp
        spacerHeightBeforeYear = 0.dp
        spacerHeightAfterYear = 16.dp
        indicatorPadding = 24.dp
    } else {
        cardHeightSize = 400.dp
        spacerHeightBeforeYear = 16.dp
        spacerHeightAfterYear = 64.dp
        indicatorPadding = 32.dp
    }

    LaunchedEffect(pagerState) {
        pagerState.animateScrollToPage(pageNumber)
    }

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

                    val quarterAndConsumptionMap =
                        quarterConsumption[page].getMappedQuarterConsumptionByYear()

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
                                            text = quarterAndConsumption[key] ?: "",
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
}