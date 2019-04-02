package com.adidas.sports.goal.vo

import android.os.Parcelable
import com.adidas.sports.goal.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoalModel(
        val id: Long,
        val title: String,
        val description: String,
        val type: GoalType,
        val goal: Int,
        val reward: RewardModel): Parcelable

@Parcelize
data class RewardModel(
        val points: Int,
        val trophy: TROPHY): Parcelable

fun GoalModel.getGoalStr(): String{
    return when(type){
        GoalType.STEP -> "goal: try to $goal step(s)"
        GoalType.RUNNING_DISTANCE,
        GoalType.WALKING_DISTANCE -> "goal: $goal meter(s)"
        else -> goal.toString()
    }
}

fun GoalModel.getTrophyStr(): String{
    return when(reward.trophy){
        TROPHY.ZOMBIE_HAND -> "zombie hand, ${reward.points} points"
        TROPHY.BRONZE_MEDAL -> "bronze medal, ${reward.points} points"
        TROPHY.SILVER_MEDAL -> "silver medal, ${reward.points} points"
        TROPHY.GOLD_MEDAL -> "gold medal, ${reward.points} points"
        else -> "zombie hand, ${reward.points} points"
    }
}

fun GoalModel.getTrophyResId(): Int{
    return when(reward.trophy){
        TROPHY.BRONZE_MEDAL ->  R.mipmap.grade_elegance
        TROPHY.SILVER_MEDAL ->  R.mipmap.grade_confidence
        TROPHY.GOLD_MEDAL ->  R.mipmap.grade_wisdom
        TROPHY.ZOMBIE_HAND -> R.mipmap.grade_superstrength
        else ->  R.mipmap.grade_elegance
    }
}


