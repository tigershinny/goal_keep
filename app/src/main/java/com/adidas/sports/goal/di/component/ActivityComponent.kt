package com.adidas.sports.goal.di.component

import com.adidas.sports.goal.di.scope.ActivityScope
import com.adidas.sports.goal.vm.MyGoalActivityVM
import dagger.Component

@Component(dependencies = [RepoComponent::class])
@ActivityScope
interface ActivityComponent {
    fun inject(arg: MyGoalActivityVM)
}