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

    /* Method to fetch the LotteryInfos stored locally */
    @Query("SELECT * FROM LotteryEntity")
    fun getAllLotteries(): Single<List<LotteryEntity>>

    /* Method to insert the Lottery fetched from api
     * to room */
    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertLotteries(lotteryEntities: List<LotteryEntity>): LongArray

    // @Insert(onConflict = OnConflictStrategy.REPLACE)
    // fun insertLottery(lotteryInfos: LotteryEntity): Long
    /* Method to get LotteryEntity by drwNo */
    //@Query("SELECT * FROM LotteryEntity WHERE drwNo=:drwNo ")
    //fun getLotteryBydrwNo(drwNo: Int): LotteryEntity
}