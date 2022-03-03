package com.demo.tandem.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.tandem.di.utils.ViewModelFactory
import com.demo.tandem.di.utils.ViewModelKey
import com.demo.tandem.viewmodel.CommunityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("UNUSED")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CommunityViewModel::class)
    abstract fun bindLabelsViewModel(viewModel: CommunityViewModel): ViewModel

}