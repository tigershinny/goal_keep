package com.adidas.sports.goal.di.component

import com.google.gson.Gson
import com.adidas.sports.goal.di.module.NetModule
import com.adidas.sports.goal.di.scope.AppScope

import dagger.Component
import okhttp3.OkHttpClient

@Component(modules = [NetModule::class], dependencies = [BaseComponent::class])
@AppScope
interface NetComponent {

    fun gson(): Gson

    fun okHttpClient(): OkHttpClient
}
