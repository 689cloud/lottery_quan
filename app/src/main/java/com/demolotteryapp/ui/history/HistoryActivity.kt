package com.demolotteryapp.ui.history


import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demolotteryapp.R
import com.demolotteryapp.data.local.db.entity.LotteryEntity
import com.demolotteryapp.ui.base.BaseActivity
import com.demolotteryapp.ui.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : BaseActivity(),HistoryNavigator {

    override fun getLayoutId(): Int {
        return R.layout.activity_history
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(HistoryViewModel::class.java)
    }

    override fun goBack(v: View) {
        finish()
    }

    override fun onSuccess(data: Any) {
        rvHistory.run {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = HistoryAdapter((data as List<LotteryEntity>).reversed())
        }
    }

    override fun onError(e: Throwable) {
        tvError.text = e.message
    }

}
