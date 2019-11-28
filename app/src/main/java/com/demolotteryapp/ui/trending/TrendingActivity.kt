package com.demolotteryapp.ui.trending

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.demolotteryapp.R
import com.demolotteryapp.ui.base.BaseActivity
import com.demolotteryapp.ui.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_history.tvError
import kotlinx.android.synthetic.main.activity_trending.*

class TrendingActivity : BaseActivity(),TrendingNavigator {

    override fun getLayoutId(): Int {
        return R.layout.activity_trending
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(TrendingViewModel::class.java)
    }

    override fun goBack(v: View) {
        finish()
    }

    override fun onError(e: Throwable) {
        tvError.text = e.message
    }

    override fun onSuccess(data: Any) {
        tvTrending.text = data as String
    }
}
