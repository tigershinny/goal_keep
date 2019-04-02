package com.adidas.sports.goal.di.component


import com.adidas.sports.goal.di.module.NetModule
import com.adidas.sports.goal.di.module.ApiModule
import com.adidas.sports.goal.di.module.DbModule
import com.adidas.sports.goal.di.module.RepoModule
import com.adidas.sports.goal.di.scope.AppScope
import com.adidas.sports.goal.repo.IRepo
import dagger.Component

@Component(modules = [RepoModule::class, ApiModule::class, DbModule::class, NetModule::class],
        dependencies = [BaseComponent::class])
@AppScope
interface RepoComponent {
    fun repo(): IRepo
}