package com.demolotteryapp.ui.main

import android.view.View
import com.demolotteryapp.ui.base.BaseNavigator

interface MainNavigator : BaseNavigator {
    fun openHistoryActivity(v: View)
    fun openTrendingActivity(v: View)
}