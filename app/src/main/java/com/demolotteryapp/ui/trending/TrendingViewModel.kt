package com.demolotteryapp.ui.trending

import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import com.demolotteryapp.data.remote.api.LotteryApiService
import com.demolotteryapp.ui.base.LotteryViewModel
import com.demolotteryapp.utils.LotteryUtils
import java.lang.StringBuilder
import javax.inject.Inject

class TrendingViewModel @Inject constructor(
    lotteryDao: LotteryDao,
    lotteryApiService: LotteryApiService
) : LotteryViewModel(lotteryDao, lotteryApiService) {

    override fun shouldGetList(): Boolean {
        return true
    }

    override fun dataResult(data: Any) {
        var trending = StringBuilder("")
        val pair = LotteryUtils.performTrending(data as List<LotteryEntity>)
        pair.forEach { item ->
            trending.append("(${item.first},${item.second})    ")
        }
        getNavigator()?.onSuccess(trending.toString())
    }

    override fun errorResult(e: Throwable) {
        getNavigator()?.onError(e)
    }

}