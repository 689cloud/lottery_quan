package com.demolotteryapp.ui.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.an.dagger.data.Resource
import com.demolotteryapp.ui.base.BaseViewModel
import com.demolotteryapp.data.remote.api.LotteryApiService
import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import com.demolotteryapp.data.repository.LotteryRepository
import javax.inject.Inject


/*
 * We are injecting the LotteryDao class
 * and the LotteryApiService class to the ViewModel.
 * */
class LotteryViewModel @Inject constructor(
    lotteryDao: LotteryDao,
    lotteryApiService: LotteryApiService
    // ,sharedPreferences : SharedPreferences
) : BaseViewModel() {

    /* You can see we are initialising the LotteryRepository class here */
    private val lotteryRepository: LotteryRepository = LotteryRepository(lotteryDao, lotteryApiService)

    /* We are using LiveData to update the UI with the data changes.
     */
    private val lottery = MutableLiveData<Resource<LotteryEntity>>()

//    private val sharedPrefsHelper = SharedPrefsHelper(sharedPreferences)


    /*
     * LiveData observed by the UI
     * */
    fun getLotteryLiveData() = lottery


    /*
     * Method called by UI to fetch lottery
     * */
    fun loadLotteryInfo(drwNo: Int) {
//        val firstTime: Boolean = sharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_FIRST_TIME,true)
//        if(firstTime){
//            Log.e("TAG","=== First Time")
//            sharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_FIRST_TIME,false);
//        }else{
//            Log.e("TAG","=== NOT First")
//        }

        compositeDisposable.add(lotteryRepository.loadLotteryBydrwNo(drwNo)
            .subscribe { resource -> getLotteryLiveData().postValue(resource) })
    }

    fun downloadLotteryData(){
        compositeDisposable.add(lotteryRepository.downloadLotteryData().subscribe({
            for (lotteryEntity: LotteryEntity in it) {
                Log.e("TAG","Lottery:" + lotteryEntity.drwNo)
            }
        }){
            Log.e("TAG"," Error:" + it)
        })
    }
}