package com.demolotteryapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import java.text.SimpleDateFormat
import java.util.*

class CommonUtils  {
    companion object {
        @SuppressLint("all")
        fun getDeviceId(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun getTimestamp(): String {
            return SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(Date())
        }
    }
}