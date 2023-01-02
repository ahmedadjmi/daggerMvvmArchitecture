package com.example.daggerproject.di.main

import com.example.daggerproject.ui.main.post.PostFragment
import com.example.daggerproject.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment
}