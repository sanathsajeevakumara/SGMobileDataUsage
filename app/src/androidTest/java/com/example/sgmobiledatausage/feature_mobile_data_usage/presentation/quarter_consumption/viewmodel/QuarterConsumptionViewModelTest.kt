@file:Suppress("DEPRECATION")

package com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.sgmobiledatausage.feature_mobile_data_usage.data.local.MobileDataUsageDao
import com.example.sgmobiledatausage.feature_mobile_data_usage.data.local.MobileDataUsageDataBase
import com.example.sgmobiledatausage.feature_mobile_data_usage.data.repository.MobileDataUsageRepositoryImpl
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.FilteredQuarterConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.QuarterConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.repository.MobileDataUsageRepository
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.usecase.QuarterConsumptionUseCase
import com.example.sgmobiledatausage.feature_mobile_data_usage.presentation.quarter_consumption.state.QuarterConsumptionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@Suppress("DEPRECATION")
internal class QuarterConsumptionViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dataBase: MobileDataUsageDataBase
    private lateinit var dao: MobileDataUsageDao
    private lateinit var repository: MobileDataUsageRepository
    private lateinit var quarterConsumptionUseCase: QuarterConsumptionUseCase
    private lateinit var quarterConsumptionViewModel: QuarterConsumptionViewModel


    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

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

        repository = MobileDataUsageRepositoryImpl(dao)
        quarterConsumptionUseCase = QuarterConsumptionUseCase(repository)
        quarterConsumptionViewModel = QuarterConsumptionViewModel(testDispatcher,quarterConsumptionUseCase)
    }

    @Test
    fun onEmptyScreenTest() {
        assertEquals(
            QuarterConsumptionState.OnScreenEmpty,
            quarterConsumptionViewModel.quarterState
        )
    }

    @Test
    fun testEmptyState() = runBlockingTest{

        val r1 = QuarterConsumption(2000,"Q1",1f)
        val r2 = QuarterConsumption(2000,"Q2",1.5f)
        val r3 = QuarterConsumption(2000,"Q3",2.5f)
        val r4 = QuarterConsumption(2000,"Q4",1f)

        val r5 = QuarterConsumption(2001,"Q1",2f)
        val r6 = QuarterConsumption(2001,"Q2",1.5f)
        val r7 = QuarterConsumption(2001,"Q3",2.5f)
        val r8 = QuarterConsumption(2001,"Q4",3f)


        val records = listOf(r1,r2,r3,r4,r5,r6,r7,r8)
        dao.insertAllMobileDataUsageRecord(records)

        quarterConsumptionViewModel.getQuarterConsumptionsByYear(2001)

        val y1GroupedConsumption = FilteredQuarterConsumption(
            year = 2000,
            quarters = "Q1,Q2,Q3,Q4",
            volumes = "1.0,1.5,2.5,1.0"
        )
        val y2GroupedConsumption = FilteredQuarterConsumption(
            year = 2001,
            quarters = "Q1,Q2,Q3,Q4",
            volumes = "2.0,1.5,2.5,3.0"
        )

        val groupedUsageList = listOf(y1GroupedConsumption,y2GroupedConsumption)

        val expected = QuarterConsumptionState.OnQuarterUsageDataAvailable(
            pageNumber = 1,
            quarterUsage = groupedUsageList
        )

        assertEquals(expected,quarterConsumptionViewModel.quarterState)

        //pageNumber=1, quarterUsage=[GroupedQuarterUsage(year=2000, quarters=1.0,1.5,2.5,1.0, volumes=Q1,Q2,Q3,Q4), GroupedQuarterUsage(year=2001, quarters=2.0,1.5,2.5,3.0, volumes=Q1,Q2,Q3,Q4)
        //pageNumber=1, quarterUsage=[GroupedQuarterUsage(year=2000, quarters=Q1,Q2,Q3,Q4, volumes=1.0,1.5,2.5,1.0), GroupedQuarterUsage(year=2001, quarters=Q1,Q2,Q3,Q4, volumes=2.0,1.5,2.5,3.0
    }


    @After
    fun tearDown() {
        dataBase.close()
    }

}