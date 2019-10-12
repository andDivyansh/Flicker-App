package com.divyansh.flicker.dependencyinjection


import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
@Singleton
@Component(
    modules = [ApiModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class]
)
interface ApiComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder

        fun build(): ApiComponent
    }

    fun inject(appController: AppController)
}
