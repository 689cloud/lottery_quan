package com.demolotteryapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.demolotteryapp.BR
import com.demolotteryapp.R
import com.demolotteryapp.databinding.SplashActivityBinding
import com.demolotteryapp.ui.base.BaseActivity
import com.demolotteryapp.ui.main.MainActivity
import com.demolotteryapp.ui.welcome.WelcomeActivity

class SplashActivity : BaseActivity(), SplashNavigator {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
         return ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    override fun openWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
