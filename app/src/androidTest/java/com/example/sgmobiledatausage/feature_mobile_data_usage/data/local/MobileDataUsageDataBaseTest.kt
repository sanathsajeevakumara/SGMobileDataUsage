package com.example.sgmobiledatausage.feature_mobile_data_usage.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.QuarterConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.YearlyTotalConsumption
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
internal class MobileDataUsageDataBaseTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: MobileDataUsageDao
    private lateinit var dataBase: MobileDataUsageDataBase

    @Before
    fun setUp() {

        dataBase = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                MobileDataUsageDataBase::class.java
            )
            .allowMainThreadQueries()
            .build()

        dao = dataBase.mobileDataUsageDao

    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    //Test 1 - Insert Quarter consumption Data list
    @Test
    fun insertConsumptionDataList() = runBlocking {

        // Usage data by row
        val row1 = QuarterConsumption(year = 2016, quarter = "Q1", volume = 4.8f)
        val row2 = QuarterConsumption(year = 2017, quarter = "Q3", volume = 2.4f)
        val row3 = QuarterConsumption(year = 2018, quarter = "Q2", volume = 8.9f)

        //insert into list
        val usageDataList = listOf(row1, row2, row3)

        //insert into the database
        dao.insertAllMobileDataUsageRecord(usageDataList)

        //get quarter usage
        val quarterConsumptionList = dao.getAllQuarterConsumption().first()

        //Check both usageDataList and quarterUsage list are same
        assertEquals(usageDataList,quarterConsumptionList)

    }

    //Test 2 - Get Yearly Consumption data
    @Test
    fun getYearlyUsageData() = runBlocking {

        //Usage data by row
        val row1 = QuarterConsumption(2018,"Q1",1f)
        val row2 = QuarterConsumption(2018,"Q2",1.5f)

        val row3 = QuarterConsumption(2019,"Q1",4f)
        val row4 = QuarterConsumption(2019,"Q2",1.5f)
        val row5 = QuarterConsumption(2019,"Q3",1.5f)
        val row6 = QuarterConsumption(2019,"Q4",3f)

        //insert into list
        val usageDataList = listOf(row1, row2, row3, row4, row5, row6)

        //insert into the database
        dao.insertAllMobileDataUsageRecord(usageDataList)

        //get yearly usage list from db
        val yearlyUsageListFromDB = dao.getYearlyTotalConsumption().first()

        //Yearly usage data by row
        val year2018Usage = YearlyTotalConsumption(year = 2018, totalConsumption = 2.5f)
        val year2019Usage = YearlyTotalConsumption(year = 2019, totalConsumption = 10.0f)

        //create yearlyUsage List
        val yearlyUsageList = listOf(year2018Usage, year2019Usage)

        assertEquals(yearlyUsageList, yearlyUsageListFromDB)

    }

    //Test 3 - Get Quarter Consumption data by year
    @Test
    fun getQuarterUsageDatByYear() = runBlocking {

        //Usage data by row
        val row1 = QuarterConsumption(2018,"Q1",1f)
        val row2 = QuarterConsumption(2018,"Q2",1.5f)

        val row3 = QuarterConsumption(2019,"Q1",4f)
        val row4 = QuarterConsumption(2019,"Q2",1.5f)
        val row5 = QuarterConsumption(2019,"Q3",1.5f)
        val row6 = QuarterConsumption(2019,"Q4",3f)

        //insert into list
        val usageDataList = listOf(row1, row2, row3, row4, row5, row6)

        //insert into the database
        dao.insertAllMobileDataUsageRecord(usageDataList)

        //get yearly usage list from db
        val yearlyGroupedQuarterUsageListDB = dao.getFilteredQuarterConsumption()

        //Yearly usage data by row
        val year2018GroupedUsage = FilteredQuarterConsumption(
            year = 2018,
            quarters = "Q1,Q2",
            volumes = "1.0,1.5"
        )
        val year2019GroupedUsage = FilteredQuarterConsumption(
            year = 2019,
            quarters = "Q1,Q2,Q3,Q4",
            volumes = "4.0,1.5,1.5,3.0"
        )

        //create yearlyUsage List
        val yearlyGroupedQuarterUsageList = listOf(year2018GroupedUsage, year2019GroupedUsage)

        assertEquals(yearlyGroupedQuarterUsageList, yearlyGroupedQuarterUsageListDB)

    }

}