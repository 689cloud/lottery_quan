package com.demolotteryapp.ui.main

import com.demolotteryapp.ui.base.BaseNavigator

interface MainNavigator : BaseNavigator {
    fun openHistoryActivity()
    fun openTrendingActivity()
    fun showRandomLotteryNumber()
    fun showWinner()
}