package com.adidas.sports.goal.vm

import androidx.lifecycle.MediatorLiveData
import com.adidas.sports.goal.di.scope.FragmentScope
import com.adidas.sports.goal.viewmodel.BaseViewModel
import com.adidas.sports.goal.repo.IRepo
import com.adidas.sports.goal.vo.GoalModel
import com.adidas.sports.goal.App
import com.adidas.sports.goal.di.component.DaggerFragmentComponent
import javax.inject.Inject


@FragmentScope
class MyGoalFragmentVM : BaseViewModel() {

    @Inject
    lateinit var repo: IRepo

    init {
        DaggerFragmentComponent.builder()
                .repoComponent(App.instance.repoComponent)
                .build().inject(this)
    }

    private val _goals = MediatorLiveData<List<GoalModel>>()
    val goals
        get() = _goals

    fun getGoals() {
        load {
            repo.fetchGoals()
        } result {
            _goals.postValue(it)
        }
    }
}


