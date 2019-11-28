package com.demolotteryapp.ui.deeplink_search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.demolotteryapp.R
import com.demolotteryapp.ui.base.BaseActivity
import com.demolotteryapp.ui.base.BaseViewModel
import com.demolotteryapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_deeplink_search.*
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*

class DeeplinkSearchActivity : BaseActivity() , DeeplinkSearchNavigator {
    private lateinit var mDeeplinkSearchViewModel: DeeplinkSearchViewModel
    override fun getLayoutId(): Int {
        return R.layout.activity_deeplink_search
    }

    override fun getViewModel(): BaseViewModel {
        mDeeplinkSearchViewModel = ViewModelProviders.of(this, viewModelFactory).get(DeeplinkSearchViewModel::class.java)
        return mDeeplinkSearchViewModel
    }

    override fun goBack(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    /** Data error return after call @API getLotteryBydrwNo */
    override fun onError(e: Throwable) {
        tvDeepError.text = e.message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data: Uri? = intent?.data
        val params =  data?.pathSegments

        params?.let {
            tvDrwNo.text = it[0]
            mDeeplinkSearchViewModel.getLotteryBydrwNo(it[0].toInt())
        }
    }
}
