package com.demolotteryapp.ui.splash

import android.content.SharedPreferences
import android.os.Handler
import com.demolotteryapp.data.local.db.dao.LotteryDao
import com.demolotteryapp.data.local.prefs.SharedPrefsHelper
import com.demolotteryapp.data.remote.api.LotteryApiService
import com.demolotteryapp.data.repository.LotteryRepository
import com.demolotteryapp.ui.base.BaseNavigator
import com.demolotteryapp.ui.base.BaseViewModel
import com.demolotteryapp.utils.AppConstants
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    lotteryDao: LotteryDao,
    lotteryApiService: LotteryApiService,
    sharedPreferences: SharedPreferences
) : BaseViewModel() {

    /* We are initialising the LotteryRepository class here */
    private val lotteryRepository: LotteryRepository =
        LotteryRepository(lotteryDao, lotteryApiService)

    private val sharedPrefsHelper = SharedPrefsHelper(sharedPreferences)

    override fun renderData(navigator: BaseNavigator) {
        super.renderData(navigator)
        if (sharedPrefsHelper.getFirstTime()) downloadLotteryData()
        else {
            Handler().postDelayed({
                (getNavigator() as SplashNavigator).openMainActivity()
            }, 2000)
        }
        sharedPrefsHelper.setFirstTime(false)
    }

    /** We just download data only one time for the first time login app.
     * This will be good for ux.
     * If downloading data is error, we can download later, at screen which use this data
     */
    fun downloadLotteryData() {
        setIsLoading(true)
        compositeDisposable.add(lotteryRepository.downloadLotteryData(AppConstants.NUM_API_DOWNLOAD).subscribe({
            openWelcomeScreen(true)
        }){
            openWelcomeScreen(false)
        })
    }

    /** Open welcome screen */
    fun openWelcomeScreen(success: Boolean) {
        setIsLoading(false)
        sharedPrefsHelper.setDownloadLotteryDataSuccess(success)
        (getNavigator() as SplashNavigator).openWelcomeActivity()
    }
}