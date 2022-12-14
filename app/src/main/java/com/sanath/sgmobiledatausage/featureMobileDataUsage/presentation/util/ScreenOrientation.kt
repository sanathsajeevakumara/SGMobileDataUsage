package com.sanath.sgmobiledatausage.featureMobileDataUsage.presentation.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isLandScape(): Boolean {
    val configuration = LocalConfiguration.current
    return when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> true
        else -> false
    }
}