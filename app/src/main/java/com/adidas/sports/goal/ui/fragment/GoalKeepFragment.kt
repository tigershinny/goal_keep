package com.adidas.sports.goal.ui.fragment

import android.os.Bundle
import android.view.View
import com.adidas.sports.goal.base.BaseFragment
import com.adidas.sports.goal.vo.GoalModel
import com.adidas.sports.goal.vo.getGoalStr
import com.adidas.sports.goal.vo.getTrophyResId
import com.adidas.sports.goal.vo.getTrophyStr
import kotlinx.android.synthetic.main.fragment_goal_keep.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import android.app.Activity
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.adidas.sports.goal.Constants.MODEL
import com.adidas.sports.goal.NetworkUtils
import com.adidas.sports.goal.R
import com.adidas.sports.goal.viewmodel.BaseViewModel
import com.adidas.sports.goal.visibleOrGone
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.Field

class GoalKeepFragment : BaseFragment<BaseViewModel>() {

    private lateinit var rAnimation: RotateAnimation

    private lateinit var model: GoalModel

    companion object {
        fun newInstance(goalModel: GoalModel): GoalKeepFragment {
            return GoalKeepFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MODEL, goalModel)
                }
            }
        }

        private const val REQUEST_OAUTH_REQUEST_CODE = 0x1001
    }

    override fun getLayoutId() = R.layout.fragment_goal_keep

    override fun generateViewModel() = BaseViewModel()

    override fun onViewCreatedFinish(view: View, savedInstanceState: Bundle?) {

        model = arguments?.getParcelable(MODEL) as GoalModel

        networkTV.visibility = (!NetworkUtils.isNetworkAvailable(context!!)).visibleOrGone()

        initFitness()

        initCard()

        stepTV.text =  activity!!.getString( R.string.total_steps, 0)
        targetTV.text = activity!!.getString( R.string.left_steps, model.goal)
        initRotateAnimation()
        refreshIB.setOnClickListener {
            refreshIB.startAnimation(rAnimation)
            readData()
        }
    }

    private fun initCard(){
        titleTV.text = model.title
        desTV.text = model.description
        goalTV.text = model.getGoalStr()
        tropyTV.text = model.getTrophyStr()
        val drawable = activity?.getDrawable(model.getTrophyResId())?.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            activity?.getDrawable(model.getTrophyResId())
        }
        tropyTV.setCompoundDrawables(drawable, null, null, null)
    }

    private fun initFitness(){
        val fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
                .build()
        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(activity!!), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    activity!!,
                    REQUEST_OAUTH_REQUEST_CODE,
                    GoogleSignIn.getLastSignedInAccount(activity!!),
                    fitnessOptions)
        } else {
            subscribe()
        }
    }

    private fun initRotateAnimation() {
        rAnimation = RotateAnimation(0f, 359f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        rAnimation.apply {
            duration = 1000
            repeatCount = -1
            interpolator = LinearInterpolator()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_OAUTH_REQUEST_CODE) {
                subscribe()
            }
        }
    }

    private fun subscribe() {
        Fitness.getRecordingClient(activity!!, GoogleSignIn.getLastSignedInAccount(activity)!!)
                .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addOnCompleteListener { task ->
                    Toast.makeText(activity,
                            if (task.isSuccessful) {
                                readData()
                                activity!!.getString(R.string.successfully_subscribed)
                            } else activity!!.getString( R.string.unsuccessfully_subscribed, task.exception),
                            Toast.LENGTH_SHORT).show()
                }
    }

    private fun readData() {
        val account =  GoogleSignIn.getLastSignedInAccount(activity!!)
        if (account == null){
            refreshIB.clearAnimation()
            Toast.makeText(activity,"have no account",
                    Toast.LENGTH_SHORT).show()
            return
        }
        Fitness.getHistoryClient(activity!!, account)
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener { dataSet ->
                    val total = (if (dataSet.isEmpty) 0 else
                        dataSet.dataPoints[0].getValue(Field.FIELD_STEPS).asInt()).toLong()
                    stepTV.text =  activity!!.getString( R.string.total_steps, total)
                    val leftSteps = total - model.goal
                    targetTV.text = if (leftSteps > 0)  activity!!.getString( R.string.left_steps, leftSteps)
                    else activity!!.getString( R.string.get_medal_suc)

                    refreshIB.clearAnimation()
                }
                .addOnFailureListener { e ->
                    stepTV.text =  activity!!.getString( R.string.problem_get_steps, e.toString())
                    refreshIB.clearAnimation()
                }
    }
}