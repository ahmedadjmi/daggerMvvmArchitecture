package com.example.daggerproject.di

import com.example.daggerproject.di.auth.AuthViewModelsModule
import com.example.daggerproject.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity


}