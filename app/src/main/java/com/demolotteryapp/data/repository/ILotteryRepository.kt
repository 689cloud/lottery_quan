package com.demolotteryapp.data.repository

import com.demolotteryapp.data.local.db.entity.LotteryEntity
import io.reactivex.Observable

interface ILotteryRepository {
    fun getLotteryByDrwNo(drwNo: Int): Observable<LotteryEntity>
    fun downloadLotteryData(amount: Int): Observable<List<LotteryEntity>>
    fun getListLotteries(drwNo: Int): Observable<List<LotteryEntity>>
}