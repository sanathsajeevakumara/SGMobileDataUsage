package com.example.sgmobiledatausage.feature_mobile_data_usage.domain.usecase

import android.util.Log
import com.example.sgmobiledatausage.feature_mobile_data_usage.core.util.SGConstValue
import com.example.sgmobiledatausage.feature_mobile_data_usage.data.remote.datastore_search_adapter.DataStoreSearchUsageInterface
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.QuarterConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.model.YearlyTotalConsumption
import com.example.sgmobiledatausage.feature_mobile_data_usage.domain.repository.MobileDataUsageRepository
import kotlinx.coroutines.flow.Flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class YearlyTotalConsumptionUseCase @Inject constructor(
    private val repository: MobileDataUsageRepository,
    private val dataStoreSearchUsageInterface: DataStoreSearchUsageInterface
) {

    fun getYearlyTotalConsumption(): Flow<List<YearlyTotalConsumption>> {
        return repository.getYearlyTotalConsumption()
    }

    suspend fun retrieveDataStoreConsumptionData() {

        try {

            val quarterConsumptionRecordData =
                dataStoreSearchUsageInterface.getMobileDataUsage(SGConstValue.RESOURCE_ID)

            if (quarterConsumptionRecordData.isCallSuccess) {

                val quarterConsumptionList =
                    quarterConsumptionRecordData.dataStoreConsumptionRecord?.map { record ->
                        QuarterConsumption(
                            year = record.getYearValue(),
                            quarter = record.getQuarterValue(),
                            volume = record.volumeOfMobileData.toFloat()
                        )
                    }

                quarterConsumptionList?.let { repository.insertAllMobileDataUsageRecord(it) }

            } else {
                Log.e(
                    SGConstValue.ERROR_TAG,
                    "Couldn't load data : No data found!"
                )
            }

        } catch (e: IOException) {
            Log.e(
                SGConstValue.ERROR_TAG,
                "Couldn't reach server, check your internet connection : ${e.printStackTrace()}"
            )
        } catch (e: HttpException) {
            Log.e(
                SGConstValue.NETWORK_ERROR_TAG,
                "Oops, something went wrong! : ${e.printStackTrace()}"
            )
        }

    }

}