package com.demolotteryapp.data.repository

import android.util.Log
import com.an.dagger.data.NetworkBoundResource
import com.an.dagger.data.Resource
import com.demolotteryapp.data.api_service.LotteryApiService
import com.demolotteryapp.data.dao.LotteryDao
import com.demolotteryapp.data.model.LotteryInfo
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Singleton

// Handle both offline and online data of Lottery
@Singleton
class LotteryRepository(
    private val lotteryDao: LotteryDao,
    private val lotteryApiService: LotteryApiService
) {
    fun loadLotteryInfoBydrwNo(drwNo: Int): LotteryInfo {
        return lotteryDao.getLotteryInfoBydrwNo(drwNo)
    }

    fun getLotteryInfoBydrwNo(drwNo: Int) {
        lotteryApiService.getLotteryInfo(drwNo);
    }

    /*
    * There are three methods called:
    * a. fetch data from server
    * b. fetch data from local
    * c. save data from api in local
    */
    fun loadLotteryBydrwNo(drwNo: Int): Observable<Resource<LotteryInfo>> {
        Log.e("TAG","=== loadLotteryBydrwNo")
        return object : NetworkBoundResource<LotteryInfo, LotteryInfo>() {
            override fun saveCallResult(item: LotteryInfo) {
                Log.e("TAG","=== saveCallResult")
                lotteryDao.insertLotteryInfo(item)
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override fun loadFromDb(): Flowable<LotteryInfo> {
                Log.e("TAG","=== loadFromDb")
                val lotteryInfo = lotteryDao.getLotteryInfoBydrwNo(drwNo)
                return if (lotteryInfo == null) {
                    Flowable.empty()
                } else Flowable.just(lotteryInfo)
            }

            override fun createCall(): Observable<Resource<LotteryInfo>> {
                Log.e("TAG","=== createCall")
                return lotteryApiService.getLotteryInfo(drwNo).flatMap { lotteryInfo ->
                    Observable.just(
                        if (lotteryInfo == null) {
                            Resource.error("Can't get the data")
                        } else Resource.success(lotteryInfo)
                    )
                }
            }
        }.getAsObservable()
    }
}