package com.adidas.sports.goal.api

data class GoalDTO(
        val id: Long,
        val title: String,
        val description: String,
        val type: String,
        val goal: Int,
        val reward: RewardDTO)

data class RewardDTO(
        val points: Int,
        val trophy: String)


data class GoalResponse(var items: List<GoalDTO>, val nextPageToken: String)


