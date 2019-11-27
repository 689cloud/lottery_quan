package com.demolotteryapp.ui.base

interface BaseNavigator {
    fun onSuccess(data : Any)
    fun onError(e : Throwable)
}