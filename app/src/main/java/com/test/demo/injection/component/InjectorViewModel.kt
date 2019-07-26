package com.test.demo.injection.component

import com.test.demo.injection.module.NetworkModule
import com.test.demo.ui.vm.PostAlbumsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface InjectorViewModel {
    fun inject(postViewModel: PostAlbumsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): InjectorViewModel

        fun networkModule(networkModule: NetworkModule): Builder

    }
}