package com.demolotteryapp.data.local.prefs

interface IPrefs {
    fun setFirstTime(firstTime: Boolean)
    fun getFirstTime(): Boolean
    fun setDownloadLotteryDataSuccess(success: Boolean)
    fun getDownloadLotteryDataSuccess(): Boolean
}