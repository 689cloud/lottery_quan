package com.demolotteryapp.di.module

import android.app.Application
import androidx.room.Room
import com.demolotteryapp.data.AppDatabase
import com.demolotteryapp.data.dao.LotteryDao
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

// Local Db Providers
@Module
class DbModule {

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
        return appDatabase.lotteryInfoDao()
    }
}
