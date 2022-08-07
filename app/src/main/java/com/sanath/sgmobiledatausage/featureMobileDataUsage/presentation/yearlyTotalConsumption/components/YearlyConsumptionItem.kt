package com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.yearlyTotalConsumption.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.sanath.sgmobiledatausage.R

@Composable
fun YearlyConsumptionItem(
    year: String,
    totalConsumption: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.year) + " $year",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.total_consumption) +  " $totalConsumption",
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}