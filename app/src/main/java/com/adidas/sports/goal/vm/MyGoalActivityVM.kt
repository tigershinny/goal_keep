package com.adidas.sports.goal.vm

import com.adidas.sports.goal.di.scope.ActivityScope
import com.adidas.sports.goal.viewmodel.BaseViewModel
import com.adidas.sports.goal.repo.IRepo

import javax.inject.Inject


@ActivityScope
class MyGoalActivityVM: BaseViewModel() {

    @Inject
    lateinit var repo: IRepo

}


