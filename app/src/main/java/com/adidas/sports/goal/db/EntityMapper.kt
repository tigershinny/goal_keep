package com.adidas.sports.goal.db

import com.adidas.sports.goal.api.GoalDTO
import com.adidas.sports.goal.api.RewardDTO

object EntityMapper{

    fun from(dto: GoalDTO, userId: Long): GoalEntity {
        return GoalEntity(dto.id, dto.title, dto.description,
                dto.type, dto.goal, from(dto.reward), userId)
    }

    fun from(dto: RewardDTO): RewardEntity {
        return RewardEntity(dto.points, dto.trophy)
    }
}