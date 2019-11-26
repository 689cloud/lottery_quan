package com.an.dagger.data

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread
protected constructor() {

    private val asObservable: Observable<Resource<ResultType>>

    init {
        val source: Observable<Resource<ResultType>>
        if (shouldFetch()) {

            source = createCall()
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    saveCallResult(processResponse(it)!!) }

                .flatMap {
                    loadFromDb().toObservable()
                        .map { Resource.success(it) } }

                .doOnError { onFetchFailed() }

                .onErrorResumeNext { t : Throwable ->
                    loadFromDb().toObservable().map {
                        Resource.error(t.message!!, it) }
                }

                .observeOn(AndroidSchedulers.mainThread())

        } else {
            source = loadFromDb()
                .toObservable()
                .map { Resource.success(it) }
        }

        asObservable = Observable.concat(
            loadFromDb()
                .toObservable()
                .map { Resource.loading(it) }
                .take(1),
            source
        )
    }


//    init {
//        val source: Observable<Resource<ResultType>>
//        if (shouldFetch()) {
//
//            source = createSourceToCallApi()
//
//        } else {
//            source = loadFromDb().toObservable()
//                .map { Resource.success(it) }
//        }
//
//        asObservable = Observable.concat(
//            loadFromDb()
//                .toObservable()
//                .map { Resource.loading(it) }
//                .take(1),
//            source
//        )
//    }
//
//    fun createSourceToCallApi(): Observable<Resource<ResultType>> {
//        return createCall()
//            .subscribeOn(Schedulers.io())
//            .doOnNext {
//                saveCallResult(processResponse(it)!!)
//            }
//
//            .flatMap {
//                loadFromDb().toObservable()
//                    .map { Resource.success(it) }
//            }
//
//            .doOnError { onFetchFailed() }
//
//            .onErrorResumeNext { t: Throwable ->
//                loadFromDb().toObservable().map {
//                    Resource.error(t.message!!, it)
//                }
//            }
//
//            .observeOn(AndroidSchedulers.mainThread())
//    }

    fun getAsObservable(): Observable<Resource<ResultType>> {
        return asObservable
    }

    private fun onFetchFailed() {}

    @WorkerThread
    protected fun processResponse(response: Resource<RequestType>): RequestType? {
        return response.data
    }

    /** Save data into local db */
    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    /** */
    @MainThread
    protected abstract fun shouldFetch(): Boolean

    /** Get data from local db */
    @MainThread
    protected abstract fun loadFromDb(): Flowable<ResultType>

    /** Call webservice and return an Observable */
    @MainThread
    protected abstract fun createCall(): Observable<Resource<RequestType>>
}