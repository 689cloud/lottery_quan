package com.demolotteryapp.ui.deeplink_search

import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.remote.api.LotteryApiService
import com.demolotteryapp.ui.base.LotteryViewModel
import javax.inject.Inject

class DeeplinkSearchViewModel @Inject constructor(
    lotteryDao: LotteryDao,
    lotteryApiService: LotteryApiService
) : LotteryViewModel(lotteryDao, lotteryApiService) {

    override fun shouldGetList(): Boolean {
        return false
    }

    override fun dataResult(data: Any) {}

    override fun errorResult(e: Throwable) {}
}