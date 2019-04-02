package com.adidas.sports.goal.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.adidas.sports.goal.Constants
import com.adidas.sports.goal.Constants.MODEL
import com.adidas.sports.goal.R
import com.adidas.sports.goal.base.BaseActivity
import com.adidas.sports.goal.ui.fragment.GoalKeepFragment
import com.adidas.sports.goal.viewmodel.BaseViewModel
import com.adidas.sports.goal.vm.MyGoalActivityVM

class GoalKeepActivity : BaseActivity<BaseViewModel>() {

    override fun generateViewModel() = BaseViewModel()

    override fun getLayoutId() = R.layout.activity_goal_keep

    override fun onViewCreated(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction()
                .add(R.id.viewContainer, GoalKeepFragment.newInstance(intent.getParcelableExtra(MODEL)))
                .commitAllowingStateLoss()
    }
}
