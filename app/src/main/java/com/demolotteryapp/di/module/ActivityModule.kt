package com.an.dagger.di.module

import com.demolotteryapp.ui.deeplink_search.DeeplinkSearchActivity
import com.demolotteryapp.ui.history.HistoryActivity
import com.demolotteryapp.ui.main.MainActivity
import com.demolotteryapp.ui.splash.SplashActivity
import com.demolotteryapp.ui.trending.TrendingActivity
import com.demolotteryapp.ui.welcome.WelcomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    //////////// ------------------------------------------------------------- ////////////
    //////////// --------------- Inject others Activity below --------------- ////////////

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeHistoryActivity(): HistoryActivity

    @ContributesAndroidInjector()
    abstract fun contributeTrendingActivity(): TrendingActivity

    @ContributesAndroidInjector()
    abstract fun contributeDeeplinkSearchActivity(): DeeplinkSearchActivity
}