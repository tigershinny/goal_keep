package com.adidas.sports.goal.repo

import com.adidas.sports.goal.vo.GoalModel

interface IRepo {
    suspend fun fetchGoals(): List<GoalModel>
}