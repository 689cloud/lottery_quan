package com.demolotteryapp.data.remote.api

import com.demolotteryapp.data.local.db.entity.LotteryEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LotteryApiService {

    @GET("common.do?method=getLottoNumber")
    fun getLottery(@Query("drwNo") drwNo: Int): Observable<LotteryEntity>
}