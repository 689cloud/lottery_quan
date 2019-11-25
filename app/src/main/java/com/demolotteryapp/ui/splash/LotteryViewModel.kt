package com.demolotteryapp.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.an.dagger.data.Resource
import com.demolotteryapp.data.api_service.LotteryApiService
import com.demolotteryapp.data.dao.LotteryDao
import com.demolotteryapp.data.model.LotteryInfo
import com.demolotteryapp.data.repository.LotteryRepository
import javax.inject.Inject

/*
 * We are injecting the MovieDao class
 * and the MovieApiService class to the ViewModel.
 * */
class LotteryViewModel @Inject constructor(
    lotteryDao: LotteryDao,
    lotteryApiService: LotteryApiService) : ViewModel() {

    /* You can see we are initialising the MovieRepository class here */
    private val lotteryRepository: LotteryRepository = LotteryRepository(lotteryDao, lotteryApiService)

    /* We are using LiveData to update the UI with the data changes.
     */
    private val lottery = MutableLiveData<Resource<LotteryInfo>>()

    /*
     * Method called by UI to fetch movies list
     * */
    fun loadLotteryInfo(drwNo: Int) {
        lotteryRepository.loadLotteryBydrwNo(drwNo)
            .subscribe { resource -> getLotteryLiveData().postValue(resource) }
    }

    /*
     * LiveData observed by the UI
     * */
    fun getLotteryLiveData() = lottery
}