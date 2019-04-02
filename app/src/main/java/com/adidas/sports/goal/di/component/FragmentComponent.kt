package com.adidas.sports.goal.di.component

import com.adidas.sports.goal.di.scope.FragmentScope
import com.adidas.sports.goal.vm.MyGoalFragmentVM
import dagger.Component

@Component(dependencies = [RepoComponent::class])
@FragmentScope
interface FragmentComponent {
    fun inject(arg: MyGoalFragmentVM)
}