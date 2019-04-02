package com.adidas.sports.goal.di.module

import com.google.gson.Gson
import com.adidas.sports.goal.net.RetrofitFactory
import com.adidas.sports.goal.BaseConfig.APP_URL
import com.adidas.sports.goal.di.scope.AppScope
import com.adidas.sports.goal.api.AppApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class ApiModule {

    @Provides
    @AppScope
    fun provideAppApi(gson: Gson, okHttpClient: OkHttpClient): AppApi {
        return RetrofitFactory.create(gson, okHttpClient, APP_URL)
                .create(AppApi::class.java)
    }
}