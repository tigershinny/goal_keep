package com.adidas.sports.goal.db

import androidx.room.*

@Entity
data class GoalEntity(
        @PrimaryKey
        val id: Long,
        val title: String,
        val description: String,
        val type: String,
        val goal: Int,
        @Embedded
        val reward: RewardEntity,
        val userId: Long)

data class RewardEntity(
        val points: Int,
        val trophy: String)