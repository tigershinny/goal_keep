package com.adidas.sports.goal.di.component

import com.adidas.sports.goal.base.BaseApplication
import com.adidas.sports.goal.di.module.BaseModule

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [BaseModule::class])
interface BaseComponent {
  fun application(): BaseApplication
}
