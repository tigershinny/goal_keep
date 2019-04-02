package com.adidas.sports.goal.base


import android.app.Application
import com.adidas.sports.goal.di.component.BaseComponent
import com.adidas.sports.goal.di.component.DaggerBaseComponent
import com.adidas.sports.goal.di.module.BaseModule
import com.adidas.sports.goal.AppUtils
import kotlin.properties.Delegates

open class BaseApplication: Application(){

    companion object {
        lateinit var appSDPath: String
        var instance: BaseApplication by Delegates.notNull()
    }
    private lateinit var baseComponent: BaseComponent


    override fun onCreate() {
        super.onCreate()

        appSDPath = AppUtils.getAppCachePath(this)

        baseComponent = DaggerBaseComponent.builder()
                .baseModule(BaseModule(this))
                .build()
    }

    fun getBaseComponent(): BaseComponent {
        return baseComponent
    }

}
