package com.demolotteryapp.ui.base

import androidx.lifecycle.MutableLiveData
import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import com.demolotteryapp.data.remote.api.LotteryApiService
import com.demolotteryapp.data.repository.LotteryRepository
import com.demolotteryapp.ui.main.MainNavigator
import com.demolotteryapp.utils.AppConstants

abstract class LotteryViewModel(
    lotteryDao: LotteryDao,
    lotteryApiService: LotteryApiService
) : BaseViewModel() {

    /* We are initialising the LotteryRepository class here */
    protected val lotteryRepository: LotteryRepository =
        LotteryRepository(lotteryDao, lotteryApiService)

    var lotteryInfo: MutableLiveData<LotteryEntity> = MutableLiveData()

    abstract fun shouldGetList() : Boolean
    abstract fun dataResult(data: Any)
    abstract fun errorResult(e: Throwable)

    override fun renderData(navigator: BaseNavigator) {
        super.renderData(navigator)
        if (shouldGetList()) getListLotteries()
    }


    /** Show all winning information from 1 drwNo to ${AppConstants.NUM_API_DOWNLOAD} drwNo. */
    fun getListLotteries() {
        setIsLoading(true)
        compositeDisposable.add(lotteryRepository.getListLotteries(AppConstants.NUM_API_DOWNLOAD).subscribe(
            {
                //AppLogger.e("TAG", "=== First:${it.first().drwNo} - Last:${it.last().drwNo}")
                if (it != null && it.size >= AppConstants.NUM_API_DOWNLOAD) {
                    setIsLoading(false)
                    dataResult(it)
                } else {
                    /** Some error here. We need to download data again. */
                    compositeDisposable.add(lotteryRepository.downloadLotteryData(AppConstants.NUM_API_DOWNLOAD).subscribe(
                        {
                            setIsLoading(false)
                            dataResult(it)
                        }) {
                        setIsLoading(false)
                        errorResult(it)
                    })
                }
            }) {
            setIsLoading(false)
            errorResult(it)
        })
    }

    fun getLotteryBydrwNo(drwNo: Int) {
        setIsLoading(true)
        compositeDisposable.add(lotteryRepository.getLotteryByDrwNo(drwNo).subscribe({
            setIsLoading(false)
            /* Update data into LiveData and show it to UI via data binding */
            lotteryInfo.value = it
        }) {
            setIsLoading(false)
            getNavigator()?.onError(it)
        })
    }
}