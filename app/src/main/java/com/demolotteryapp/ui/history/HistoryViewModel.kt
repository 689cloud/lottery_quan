package com.demolotteryapp.ui.history

import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import com.demolotteryapp.data.remote.api.LotteryApiService
import com.demolotteryapp.ui.base.LotteryViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    lotteryDao: LotteryDao,
    lotteryApiService: LotteryApiService
) : LotteryViewModel(lotteryDao, lotteryApiService){

    override fun shouldGetList(): Boolean {
        return true
    }
    override fun dataResult(data : Any) {
        getNavigator()?.onSuccess(data as List<LotteryEntity>)
    }

    override fun errorResult(e: Throwable) {
       getNavigator()?.onError(e)
    }

}