package com.adidas.sports.goal.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.adidas.sports.goal.R
import com.adidas.sports.goal.base.BaseActivity
import com.adidas.sports.goal.ui.fragment.MyGoalFragment
import com.adidas.sports.goal.vm.MyGoalActivityVM
import android.content.Intent



class MyGoalActivity : BaseActivity<MyGoalActivityVM>() {

    override fun generateViewModel()
            = ViewModelProviders.of(this).get(MyGoalActivityVM::class.java)

    override fun getLayoutId()= R.layout.activity_my_goal

    override fun onViewCreated(savedInstanceState: Bundle?) {

        supportFragmentManager.beginTransaction()
                .add(R.id.viewContainer, MyGoalFragment.newInstance())
                .commitAllowingStateLoss()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
