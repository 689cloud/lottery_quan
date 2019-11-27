package com.demolotteryapp.data.local.prefs


import android.content.SharedPreferences
import javax.inject.Singleton

@Singleton
class SharedPrefsHelper(private val mSharedPreferences: SharedPreferences) : IPrefs {

    companion object {

        /** To save first time launch the app */
        var PREF_KEY_FIRST_TIME = "first_time"

        /** To check the lottery data was downloaded success or not */
        var PREF_KEY_DOWNLOAD_LOTTERY_DATA = "download_lottery_data"
    }

    override fun setDownloadLotteryDataSuccess(success: Boolean) {
        put(PREF_KEY_DOWNLOAD_LOTTERY_DATA, success)
    }

    override fun getDownloadLotteryDataSuccess(): Boolean {
        return get(PREF_KEY_DOWNLOAD_LOTTERY_DATA, true)
    }

    override fun setFirstTime(firstTime: Boolean) {
        put(PREF_KEY_FIRST_TIME, firstTime)
    }

    override fun getFirstTime(): Boolean {
        return get(PREF_KEY_FIRST_TIME, true)
    }

    fun put(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String, defaultValue: String): String? {
        return mSharedPreferences.getString(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Int): Int {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Float): Float {
        return mSharedPreferences.getFloat(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Boolean): Boolean {
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    fun deleteSavedData(key: String) {
        mSharedPreferences.edit().remove(key).apply()
    }


}