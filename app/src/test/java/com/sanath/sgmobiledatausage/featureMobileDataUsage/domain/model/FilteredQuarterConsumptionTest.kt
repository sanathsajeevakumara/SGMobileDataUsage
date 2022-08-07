package com.sanath.sgmobiledatausage.featureMobileDataUsage.domain.model

import org.junit.Assert.*
import org.junit.Test

internal class FilteredQuarterConsumptionTest {

    @Test
    fun `map quarter consumption by year` () {

        val filteredQuarterConsumption = FilteredQuarterConsumption(
            year = 2006,
            quarters = "Q1,Q2,Q3",
            volumes = "10.0,12.3,14.5"
        )

        val quarterConsumptionByYear = filteredQuarterConsumption.getMappedQuarterConsumptionByYear()

        assertEquals(
            mapOf(
                "Q1" to "10.0",
                "Q2" to "12.3",
                "Q3" to "14.5"
            ), quarterConsumptionByYear
        )

    }

}