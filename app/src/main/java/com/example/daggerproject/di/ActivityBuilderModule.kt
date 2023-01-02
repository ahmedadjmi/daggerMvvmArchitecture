package com.example.daggerproject.di


import com.example.daggerproject.di.auth.AuthModule
import com.example.daggerproject.di.auth.AuthScope
import com.example.daggerproject.di.auth.AuthViewModelsModule
import com.example.daggerproject.di.main.MainFragmentBuilderModule
import com.example.daggerproject.di.main.MainModule
import com.example.daggerproject.di.main.MainScope
import com.example.daggerproject.di.main.MainViewModelModule
import com.example.daggerproject.ui.auth.AuthActivity
import com.example.daggerproject.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class, MainViewModelModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}