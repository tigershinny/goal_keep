package com.adidas.sports.goal.di.module

import com.adidas.sports.goal.base.BaseApplication

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class BaseModule(private val application: BaseApplication) {

    @Provides
    @Singleton
    fun providesBaseApplication(): BaseApplication {
        return application
    }
}
