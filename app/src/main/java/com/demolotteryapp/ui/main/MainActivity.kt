package com.demolotteryapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demolotteryapp.R
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import com.demolotteryapp.databinding.MainActivityBinding
import com.demolotteryapp.ui.base.BaseActivity
import com.demolotteryapp.ui.base.BaseViewModel
import com.demolotteryapp.ui.history.HistoryActivity
import com.demolotteryapp.ui.trending.TrendingActivity
import com.demolotteryapp.utils.AppLogger
import com.demolotteryapp.utils.LotteryUtils
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(), MainNavigator {
    private lateinit var mMainViewModel: MainViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): BaseViewModel {
        mMainViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        return mMainViewModel
    }

    override fun openHistoryActivity(v: View) {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    override fun openTrendingActivity(v: View) {
        val intent = Intent(this, TrendingActivity::class.java)
        startActivity(intent)
    }

    /** Data error return after call @API getLotteryBydrwNo */
    override fun onError(e: Throwable) {
        tvLottery.text = e.message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        edtEvent.textChanges().skipInitialValue().map {
            it.toString().toIntOrNull() ?: 0
        }.debounce(300, TimeUnit.MILLISECONDS).subscribe {
            mMainViewModel.getLotteryBydrwNo(it)
        }

    }


}
