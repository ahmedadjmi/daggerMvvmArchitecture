package com.example.daggerproject.di.main

import androidx.lifecycle.ViewModel
import com.example.daggerproject.di.ViewModelKey
import com.example.daggerproject.ui.main.post.PostViewModel
import com.example.daggerproject.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel
}