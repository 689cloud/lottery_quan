package com.demolotteryapp.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.demolotteryapp.data.local.db.AppDatabase
import com.demolotteryapp.data.local.db.dao.LotteryDao
import dagger.Module
import dagger.Provides

import javax.inject.Singleton
import android.content.SharedPreferences



// Local Db Providers
@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideSharedPreference(application: Application): SharedPreferences {
        return application.getSharedPreferences("LotteryDemoApp-SharedPrefs", Context.MODE_PRIVATE)
    }

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, "Lottery.db")
            .allowMainThreadQueries().build()
    }



    /*
     * We need the LotteryDao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */
    @Provides
    @Singleton
    internal fun provideLotteryDao(appDatabase: AppDatabase): LotteryDao {
        return appDatabase.lotteryDao()
    }
}
