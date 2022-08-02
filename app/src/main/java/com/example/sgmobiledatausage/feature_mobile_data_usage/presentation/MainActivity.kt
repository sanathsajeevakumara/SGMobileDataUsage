package com.example.sgmobiledatausage.feature_mobile_data_usage.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.NavGraphs
import com.example.sgmobiledatausage.ui.theme.SGMobileDataUsageTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGMobileDataUsageTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}