package com.demolotteryapp.data.api_service

import com.demolotteryapp.data.model.LotteryInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LotteryApiService {

    @GET("common.do?method=getLottoNumber")
    fun getLotteryInfo(@Query("drwNo") drwNo: Int): Observable<LotteryInfo>
}