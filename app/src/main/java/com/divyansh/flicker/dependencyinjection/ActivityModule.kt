package com.divyansh.flicker.dependencyinjection

import com.divyansh.flicker.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
@Module
abstract class ActivityModule {

        @ContributesAndroidInjector
        abstract fun contributeMainActivity(): MainActivity

}