package com.demolotteryapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demolotteryapp.data.dao.LotteryDao
import com.demolotteryapp.data.model.LotteryInfo

@Database(entities = [LotteryInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lotteryInfoDao(): LotteryDao
}