package com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sgmobiledatausage.R
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption
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
                .height(400.dp)
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

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.year) + " ${quarterConsumption[page].year}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(64.dp))

                val quarterConsumptionMap = quarterConsumption[page].getMappedQuarterConsumptionByYear()

                quarterConsumptionMap?.let { quarterUsage ->
                    if (quarterUsage.isNotEmpty()) {
                        for (key in quarterUsage.keys) {

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
                                        text = "$key :",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        text = quarterConsumptionMap[key]?:"",
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
            .padding(bottom = 32.dp),
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