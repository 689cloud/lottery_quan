package com.an.dagger.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.an.dagger.factory.ViewModelFactory
import com.demolotteryapp.di.ViewModelKey
import com.demolotteryapp.ui.splash.LotteryViewModel
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
     * with the  LotteryViewModel.class as key,
     * and a Provider that will build a LotteryViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(LotteryViewModel::class)
    protected abstract fun lotteryViewModel(lotteryViewModel: LotteryViewModel): ViewModel
}