package com.demolotteryapp.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel : ViewModel(), IBaseViewModel {
    /* To release Observable */
    protected val compositeDisposable = CompositeDisposable()

    /* Show loading by data binding */
    private val mIsLoading = ObservableBoolean()

    /* Interface for screen */
    private var mNavigator: WeakReference<BaseNavigator>? = null

    override fun renderData(navigator: BaseNavigator) {
        setNavigator(navigator)
    }

    private fun setNavigator(navigator: BaseNavigator) {
        this.mNavigator = WeakReference(navigator)
    }

    fun getNavigator(): BaseNavigator? {
        return mNavigator?.get()
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    /**  Release observable data */
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}