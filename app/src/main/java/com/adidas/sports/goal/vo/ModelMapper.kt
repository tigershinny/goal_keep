package com.adidas.sports.goal.vo

import com.adidas.sports.goal.db.GoalEntity
import com.adidas.sports.goal.db.RewardEntity

object ModelMapper{

    fun from(entity: GoalEntity): GoalModel {
        return GoalModel(entity.id, entity.title, entity.description,
                GoalType.from(entity.type), entity.goal, from(entity.reward))
    }

    fun from(entity: RewardEntity): RewardModel {
        return RewardModel(entity.points, TROPHY.from(entity.trophy))
    }
}