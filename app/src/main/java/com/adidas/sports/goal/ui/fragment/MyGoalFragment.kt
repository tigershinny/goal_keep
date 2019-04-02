package com.adidas.sports.goal.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adidas.sports.goal.Constants.MODEL
import com.adidas.sports.goal.NetworkUtils
import com.adidas.sports.goal.R
import com.adidas.sports.goal.base.BaseFragment
import com.adidas.sports.goal.widgets.RecyclerItemDecoration
import com.adidas.sports.goal.ui.activity.GoalKeepActivity
import com.adidas.sports.goal.ui.adapter.GoalRA
import com.adidas.sports.goal.visibleOrGone
import com.adidas.sports.goal.vm.MyGoalFragmentVM
import kotlinx.android.synthetic.main.fragment_my_goal.*

class MyGoalFragment : BaseFragment<MyGoalFragmentVM>() {

    companion object {
        fun newInstance(): MyGoalFragment {
            return MyGoalFragment()
        }
    }

    override fun getLayoutId() = R.layout.fragment_my_goal

    override fun generateViewModel(): MyGoalFragmentVM {
        return ViewModelProviders.of(this).get(MyGoalFragmentVM::class.java)
    }

    override fun onViewCreatedFinish(view: View, savedInstanceState: Bundle?) {

        networkTV.visibility = (!NetworkUtils.isNetworkAvailable(context!!)).visibleOrGone()

        goalRV.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        goalRV.addItemDecoration(RecyclerItemDecoration(resources.getDimensionPixelSize(R.dimen.x15)))
        goalRV.setEmptyView(emptyTV)
        viewModel.goals.observe(this, Observer {
            activity?.run {
                goalRV.adapter = GoalRA(this, it) {
                    startActivity(Intent(this, GoalKeepActivity::class.java).apply {
                        putExtra(MODEL, it)
                    })
                }
            }
        })

        viewModel.getGoals()
    }

}