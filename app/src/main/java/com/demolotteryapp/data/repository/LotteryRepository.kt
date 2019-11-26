package com.demolotteryapp.data.repository

import android.util.Log
import com.an.dagger.data.NetworkBoundResource
import com.an.dagger.data.Resource
import com.demolotteryapp.data.remote.api.LotteryApiService
import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

// Handle both offline and online data of Lottery
@Singleton
class LotteryRepository(
    private val lotteryDao: LotteryDao,
    private val lotteryApiService: LotteryApiService
) {
    fun loadLotteryInfoBydrwNo(drwNo: Int): LotteryEntity {
        return lotteryDao.getLotteryBydrwNo(drwNo)
    }

    fun getLotteryInfoBydrwNo(drwNo: Int) {
        lotteryApiService.getLottery(drwNo);
    }

    /*
    * There are three methods called:
    * a. fetch data from server
    * b. fetch data from local
    * c. save data from api in local
    */
    fun loadLotteryBydrwNo(drwNo: Int): Observable<Resource<LotteryEntity>> {
        Log.e("TAG", "=== loadLotteryBydrwNo")
        return object : NetworkBoundResource<LotteryEntity, LotteryEntity>() {
            override fun saveCallResult(item: LotteryEntity) {
                Log.e("TAG", "=== saveCallResult")
                lotteryDao.insertLottery(item)
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override fun loadFromDb(): Flowable<LotteryEntity> {
                Log.e("TAG", "=== loadFromDb")
                val lotteryInfo = lotteryDao.getLotteryBydrwNo(drwNo)
                return if (lotteryInfo == null) {
                    Flowable.empty()
                } else Flowable.just(lotteryInfo)
            }

            override fun createCall(): Observable<Resource<LotteryEntity>> {
                Log.e("TAG", "=== createCall")
                return lotteryApiService.getLottery(drwNo).flatMap { lotteryInfo ->
                    Observable.just(
                        if (lotteryInfo == null) {
                            Resource.error("Can't get the data")
                        } else Resource.success(lotteryInfo)
                    )
                }
            }
        }.getAsObservable()
    }

    fun downloadLotteryData(): Observable<ArrayList<LotteryEntity>> {
        val requests = ArrayList<Observable<LotteryEntity>>()

        for (i in 1..50) {
            requests.add(lotteryApiService.getLottery(i))
        }
        return Observable
            .zip(requests) {
                // do something with those results and emit new event

                Log.e("onSubscribe", "apply: " + it.size)
                val dataResponse = ArrayList<LotteryEntity>()
                for (i in it) {
                    dataResponse.add(i as LotteryEntity)
                }

                dataResponse
                // <-- Here we emit just new empty Object(), but you can emit anything
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
    }

}