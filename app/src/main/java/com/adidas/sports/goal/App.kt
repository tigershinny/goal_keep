package com.adidas.sports.goal

import android.content.Context
import androidx.multidex.MultiDex
import com.adidas.sports.goal.base.BaseApplication
import com.adidas.sports.goal.di.component.DaggerRepoComponent
import com.adidas.sports.goal.di.component.RepoComponent
import com.adidas.sports.goal.di.module.ApiModule
import com.adidas.sports.goal.di.module.DbModule
import com.adidas.sports.goal.di.module.RepoModule
import kotlin.properties.Delegates

class App : BaseApplication() {

    lateinit var repoComponent: RepoComponent

    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        repoComponent = DaggerRepoComponent.builder()
                .apiModule(ApiModule())
                .dbModule(DbModule())
                .repoModule(RepoModule())
                .baseComponent(getBaseComponent())
                .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}