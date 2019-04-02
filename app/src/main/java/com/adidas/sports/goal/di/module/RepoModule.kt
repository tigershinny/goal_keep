package com.adidas.sports.goal.di.module

import com.adidas.sports.goal.di.scope.AppScope
import com.adidas.sports.goal.api.AppApi
import com.adidas.sports.goal.base.BaseApplication
import com.adidas.sports.goal.db.GoalDao
import com.adidas.sports.goal.repo.IRepo
import com.adidas.sports.goal.repo.Repo
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    @AppScope
    fun provideRepo(appApi: AppApi,
                    goadDao: GoalDao, baseApplication: BaseApplication): IRepo {
        return Repo(appApi, goadDao, baseApplication)
    }
}