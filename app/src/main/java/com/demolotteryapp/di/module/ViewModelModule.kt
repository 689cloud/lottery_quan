package com.an.dagger.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.an.dagger.factory.ViewModelFactory
import com.demolotteryapp.di.ViewModelKey
import com.demolotteryapp.ui.main.MainViewModel
import com.demolotteryapp.ui.splash.SplashViewModel
import com.demolotteryapp.ui.welcome.WelcomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  ViewModel.class as key,
     * and a Provider that will build a ViewModel
     * object.
     * */
    //////////// ------------------------------------------------------------- ////////////
    //////////// --------------- Inject others ViewModel below --------------- ////////////

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    protected abstract fun splashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    protected abstract fun welcomeViewModel(welcomeViewModel : WelcomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}