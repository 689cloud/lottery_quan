package com.demolotteryapp.data.repository

import android.util.Log
import com.an.dagger.data.NetworkBoundResource
import com.an.dagger.data.Resource
import com.demolotteryapp.data.remote.api.LotteryApiService
import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import com.demolotteryapp.utils.AppLogger
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Singleton

// Handle both offline and online data of Lottery
@Singleton
class LotteryRepository(
    private val lotteryDao: LotteryDao,
    private val lotteryApiService: LotteryApiService
) : ILotteryRepository {


    /** We download lottery info from 1 to 50 and save into local db */
    override fun downloadLotteryData(): Observable<List<LotteryEntity>> {
        val requests = ArrayList<Observable<LotteryEntity>>()

        for (i in 1..50) {
            requests.add(lotteryApiService.getLottery(i))
        }
        return Observable
            .zip(requests) {
                // do something with those results and emit new event
                AppLogger.d("onSubscribe", "apply: " + it.size)

                var dataResponse: List<LotteryEntity> = it.map { i -> i as LotteryEntity }
                storeLotteriesInDb(dataResponse)

                dataResponse
                // <-- Here we emit just new empty Object(), but you can emit anything
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
    }

    /**
     * We get data from local first.
     * Return it if it's exists, else call api from server and save it into local db.
     * The data is fixed, so we don't need to call api and update data into local db.
     */
    override fun getLotteryByDrwNo(drwNo: Int): Observable<LotteryEntity> {
        return getLotteryFromDb(drwNo)
            .onErrorResumeNext(getLotteryFromApi(drwNo))

        /** As below, we show the data from local first,
         * and call api and update data into local db */
//        return Observable.concat(
//            getLotteryFromDb(drwNo).onErrorResumeNext(Observable.empty()),
//            getLotteryFromApi(drwNo))
    }


    /** Get item from local db by using Room */
    private fun getLotteryFromDb(drwNo: Int): Observable<LotteryEntity> {
        return lotteryDao.getLotteryBydrwNo(drwNo)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /** Get data from server by using Retrofit2 */
    private fun getLotteryFromApi(drwNo: Int): Observable<LotteryEntity> {
        return lotteryApiService.getLottery(drwNo)
            .doOnNext {
                storeLotteryInDb(it)
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /** Save data into local db by using Room */
    private fun storeLotteryInDb(lotteryEntity: LotteryEntity) {
        Observable.fromCallable { lotteryDao.insert(lotteryEntity) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {}
    }

    /** Save list lotteries into local db by using Room */
    private fun storeLotteriesInDb(lotteryEntities: List<LotteryEntity>) {
        Observable.fromCallable { lotteryDao.insertAll(lotteryEntities) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {}
    }


    /*
    * There are three methods called:
    * a. fetch data from server
    * b. fetch data from local
    * c. save data from api in local
    */
//    fun loadLotteryBydrwNo(drwNo: Int): Observable<Resource<LotteryEntity>> {
//        Log.e("TAG", "=== loadLotteryBydrwNo")
//        return object : NetworkBoundResource<LotteryEntity, LotteryEntity>() {
//            override fun saveCallResult(item: LotteryEntity) {
//                Log.e("TAG", "=== saveCallResult")
//                lotteryDao.insertLottery(item)
//            }
//
//            override fun shouldFetch(): Boolean {
//                return true
//            }
//
//            override fun loadFromDb(): Flowable<LotteryEntity> {
//                Log.e("TAG", "=== loadFromDb")
//                val lotteryInfo = lotteryDao.getLotteryBydrwNo(drwNo)
//                return if (lotteryInfo == null) {
//                    Flowable.empty()
//                } else Flowable.just(lotteryInfo)
//            }
//
//            override fun createCall(): Observable<Resource<LotteryEntity>> {
//                Log.e("TAG", "=== createCall")
//                return lotteryApiService.getLottery(drwNo).flatMap { lotteryInfo ->
//                    Observable.just(
//                        if (lotteryInfo == null) {
//                            Resource.error("Can't get the data")
//                        } else Resource.success(lotteryInfo)
//                    )
//                }
//            }
//        }.getAsObservable()
//    }
}