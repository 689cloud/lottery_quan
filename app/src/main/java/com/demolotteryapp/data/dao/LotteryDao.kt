package com.demolotteryapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demolotteryapp.data.model.LotteryInfo

@Dao
interface LotteryDao {
    /* Method to insert the movies fetched from api
     * to room */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLotteryInfos(lotteryInfos: List<LotteryInfo>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLotteryInfo(lotteryInfos: LotteryInfo): Long

    /* Method to fetch the LotteryInfos stored locally */
    @Query("SELECT * FROM 'LotteryInfo'")
    fun getAllLotteryInfos(): List<LotteryInfo>

    /* Method to get LotteryInfo by drwNo */
    @Query("SELECT * FROM 'LotteryInfo' WHERE drwNo=:drwNo ")
    fun getLotteryInfoBydrwNo(drwNo: Int): LotteryInfo
}