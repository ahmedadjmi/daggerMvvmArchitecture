package com.example.daggerproject.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)