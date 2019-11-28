package com.demolotteryapp.ui.main

import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.remote.api.LotteryApiService
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import com.demolotteryapp.ui.WinnerType
import com.demolotteryapp.ui.base.LotteryViewModel
import com.demolotteryapp.utils.LotteryUtils


class MainViewModel @Inject constructor(
    lotteryDao: LotteryDao,
    lotteryApiService: LotteryApiService
) : LotteryViewModel(lotteryDao, lotteryApiService) {

    var randomLotteryNumber: MutableLiveData<List<Int>> = MutableLiveData()
    var winnerType: MutableLiveData<WinnerType> = MutableLiveData()

    override fun shouldGetList(): Boolean {
        return false
    }

    override fun dataResult(data: Any) {}

    override fun errorResult(e: Throwable) {}

    fun checkWinner() {
        winnerType.value = LotteryUtils.checkWinner(randomLotteryNumber.value, lotteryInfo.value)
    }

    fun generateRandomLotteryNumber() {
        randomLotteryNumber.value = LotteryUtils.generateRandomLotteryNumber(1, 45, 6)
    }


}