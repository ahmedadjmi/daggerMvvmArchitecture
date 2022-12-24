package com.example.daggerproject.di

import androidx.lifecycle.ViewModelProvider
import com.example.daggerproject.ui.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(modelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}