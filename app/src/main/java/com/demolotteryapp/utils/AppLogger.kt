package com.demolotteryapp.utils

import android.util.Log
import com.demolotteryapp.BuildConfig
import java.util.logging.Logger

class AppLogger {
    companion object {
        fun isDev(): Boolean {
            return BuildConfig.DEBUG
        }

        fun w(tag: String, message: String) {
            if (isDev()) Log.w(tag, message)
        }

        fun d(tag: String, message: String) {
            if (isDev()) Log.d(tag, message)
        }

        fun e(tag: String, message: String) {
            if (isDev()) Log.e(tag, message)
        }
    }


}