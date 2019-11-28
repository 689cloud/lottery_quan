package com.demolotteryapp.ui.history

import android.view.View
import com.demolotteryapp.ui.base.BaseNavigator

interface HistoryNavigator : BaseNavigator{
    fun goBack(v:View)
}