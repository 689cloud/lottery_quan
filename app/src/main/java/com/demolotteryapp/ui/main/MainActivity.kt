package com.demolotteryapp.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.demolotteryapp.R
import com.demolotteryapp.ui.base.BaseActivity
import com.demolotteryapp.ui.base.BaseViewModel

class MainActivity : BaseActivity(), MainNavigator {
    private lateinit var mMainViewModel: MainViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): BaseViewModel {
        mMainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        return mMainViewModel
    }

    override fun openHistoryActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openTrendingActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRandomLotteryNumber() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showWinner() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
