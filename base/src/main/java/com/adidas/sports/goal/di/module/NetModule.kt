package com.adidas.sports.goal.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.adidas.sports.goal.base.BaseApplication
import com.adidas.sports.goal.di.scope.AppScope

import java.io.File
import java.util.concurrent.TimeUnit


import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient

@Module
class NetModule {

    @AppScope
    @Provides
    fun provideOkHttpCache(): Cache {
        return Cache(File(BaseApplication.appSDPath, "http_cache"), 20 * 1024 * 1024) //20 MB
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
       val builder = OkHttpClient.Builder()

        builder.connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
        builder.cache(cache)
        return builder.build()
    }

    @AppScope
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

}