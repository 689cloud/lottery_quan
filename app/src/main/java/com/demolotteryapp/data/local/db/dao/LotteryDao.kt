package com.demolotteryapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import io.reactivex.Single

@Dao
interface LotteryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(lotteryEntities: List<LotteryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lotteryInfos: LotteryEntity)

    /* Method to get LotteryEntity by drwNo */
    @Query("SELECT * FROM LotteryEntity WHERE drwNo=:drwNo ")
    fun getLotteryBydrwNo(drwNo: Int): Single<LotteryEntity>

    /* Method to fetch the LotteryEntity stored locally */
    @Query("SELECT * FROM LotteryEntity")
    fun getAllLotteries(): Single<List<LotteryEntity>>

    /* Method to get list lotteries which have drwNo column <= @drwNo */
    @Query("SELECT * FROM LotteryEntity WHERE drwNo<=:drwNo")
    fun getListLotteries(drwNo: Int): Single<List<LotteryEntity>>
}