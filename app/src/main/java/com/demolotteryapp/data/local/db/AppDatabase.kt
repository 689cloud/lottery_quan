package com.demolotteryapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.local.db.entity.LotteryEntity

@Database(entities = [LotteryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lotteryDao(): LotteryDao
}