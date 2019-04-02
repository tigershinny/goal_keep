package com.adidas.sports.goal.di.module

import com.adidas.sports.goal.base.BaseApplication
import com.adidas.sports.goal.di.scope.AppScope
import com.adidas.sports.goal.db.GoalDao
import com.adidas.sports.goal.db.getAppDataBase
import dagger.Module
import dagger.Provides

@Module
class DbModule {
    @Provides
    @AppScope
    fun provideStudyingCourseDao(application: BaseApplication): GoalDao {
        return getAppDataBase(application).goalDao()
    }
}
