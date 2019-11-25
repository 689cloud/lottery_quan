package com.an.dagger.data

import com.demolotteryapp.data.Status
import com.demolotteryapp.data.Status.ERROR
import com.demolotteryapp.data.Status.LOADING
import com.demolotteryapp.data.Status.SUCCESS


class Resource<T> private constructor(val status: Status, val data: T?, val message: String?) {

    val isSuccess: Boolean
        get() = status === SUCCESS && data != null

    val isLoading: Boolean
        get() = status === LOADING

    val isLoaded: Boolean
        get() = status !== LOADING

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(ERROR, null, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}