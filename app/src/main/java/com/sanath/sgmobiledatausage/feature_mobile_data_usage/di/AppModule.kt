package com.sanath.sgmobiledatausage.feature_mobile_data_usage.di

import android.app.Application
import androidx.room.Room
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.core.util.SGConstValue
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.local.MobileDataUsageDao
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.local.MobileDataUsageDataBase
import com.sanath.sgmobiledatausage.feature_mobile_data_usage.data.remote.SGMobileDataUsageAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideRetrofit(): SGMobileDataUsageAPI {
        return Retrofit.Builder()
            .baseUrl(SGConstValue.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SGMobileDataUsageAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDataUsageDataBase(app: Application): MobileDataUsageDataBase {
        return Room.databaseBuilder(
            app,
            MobileDataUsageDataBase::class.java,
            SGConstValue.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: MobileDataUsageDataBase): MobileDataUsageDao {
        return db.mobileDataUsageDao
    }

}