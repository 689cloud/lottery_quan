package com.an.dagger.di.module

import com.demolotteryapp.ui.base.BaseFragment
import com.demolotteryapp.ui.splash.SplashActivity
import com.demolotteryapp.ui.welcome.WelcomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeWelcomeActivity(): WelcomeActivity

}