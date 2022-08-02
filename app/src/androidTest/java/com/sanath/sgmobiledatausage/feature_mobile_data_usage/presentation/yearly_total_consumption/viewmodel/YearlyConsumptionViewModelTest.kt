@file:Suppress("DEPRECATION")

package com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.local.MobileDataUsageDao
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.local.MobileDataUsageDataBase
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter.DataStoreSearchUsageImplTest
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.remote.response_model.Record
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.repository.MobileDataUsageRepositoryImpl
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.model.YearlyTotalConsumption
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.repository.MobileDataUsageRepository
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.domain.usecase.YearlyTotalConsumptionUseCase
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.presentation.yearly_total_consumption.state.YearlyConsumptionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Rule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class YearlyConsumptionViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dataBase: MobileDataUsageDataBase
    private lateinit var dao: MobileDataUsageDao
    private lateinit var repository: MobileDataUsageRepository
    private lateinit var yearlyConsumptionUseCase: YearlyTotalConsumptionUseCase
    private lateinit var mockDataStoreSearchUsage: DataStoreSearchUsageImplTest
    private lateinit var yearlyConsumptionViewModel: YearlyConsumptionViewModel


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

        //Record
        val record1 = Record(1,"2000-Q1","10.5")
        val record2 = Record(2,"2000-Q2","4.5")
        val record3 = Record(3,"2000-Q3","2.5")
        val record4 = Record(4,"2000-Q4","2.5")

        //Record List
        val mockRecordList = listOf(record1,record2,record3,record4)

        mockDataStoreSearchUsage = DataStoreSearchUsageImplTest(mockData = mockRecordList)

        repository = MobileDataUsageRepositoryImpl(dao = dao)

        yearlyConsumptionUseCase = YearlyTotalConsumptionUseCase(
            repository = repository,
            dataStoreSearchUsageInterface = mockDataStoreSearchUsage
        )

        yearlyConsumptionViewModel = YearlyConsumptionViewModel(
            dispatcher = testDispatcher,
            yearlyTotalConsumptionUseCase = yearlyConsumptionUseCase
        )

    }

    @Test
    fun onEmptyScreenTest() {
        assertEquals(
            YearlyConsumptionState.OnEmptyScreen,
            yearlyConsumptionViewModel.state
        )
    }

    @Test
    fun yearlyUsageTest() = runBlockingTest {

        yearlyConsumptionViewModel.getYearlyTotalConsumptionData()
        yearlyConsumptionViewModel.fetchDataStoreSearchData()

        val yearlyUsage = YearlyConsumptionState.OnYearlyConsumptionAvailable(
            listOf(YearlyTotalConsumption(year = 2000, totalConsumption = 20.0f))
        )

        assertEquals(
            yearlyUsage,
            yearlyConsumptionViewModel.state
        )

    }

    @After
    fun tearDown() {
        dataBase.close()
    }

}