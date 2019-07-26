package com.test.demo.injection

import android.arch.lifecycle.ViewModel
import com.test.demo.injection.component.DaggerInjectorViewModel
import com.test.demo.injection.component.InjectorViewModel
import com.test.demo.injection.module.NetworkModule
import com.test.demo.ui.vm.PostAlbumsViewModel


abstract class BaseViewModel : ViewModel() {

    private val injectorViewModel: InjectorViewModel =
        DaggerInjectorViewModel.builder().networkModule(NetworkModule).build()

    init {
        inject()
    }


    private fun inject() {
        when (this) {
            is PostAlbumsViewModel -> injectorViewModel.inject(this)
        }
    }
}