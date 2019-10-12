package com.divyansh.flicker.dependencyinjection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.divyansh.flicker.viewmodel.FlickerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FlickerViewModel::class)
    protected abstract fun movieListViewModel(moviesListViewModel: FlickerViewModel): ViewModel


}