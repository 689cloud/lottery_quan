package com.demolotteryapp.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.demolotteryapp.BR
import com.demolotteryapp.R
import com.demolotteryapp.ui.base.BaseActivity
import com.demolotteryapp.ui.base.BaseViewModel
import com.demolotteryapp.ui.main.MainActivity


class WelcomeActivity : BaseActivity(), WelcomeNavigator {

    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(WelcomeViewModel::class.java)
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun gotoMain(v: View) {
        openMainActivity()
    }
}
