package com.adidas.sports.goal.repo

import com.adidas.sports.goal.NetworkUtils
import com.adidas.sports.goal.api.AppApi
import com.adidas.sports.goal.api.GoalDTO
import com.adidas.sports.goal.api.GoalResponse
import com.adidas.sports.goal.api.RewardDTO
import com.adidas.sports.goal.base.BaseApplication
import com.adidas.sports.goal.exception.ExceptionTransformer
import com.adidas.sports.goal.net.BaseResponse
import com.adidas.sports.goal.di.scope.AppScope
import com.adidas.sports.goal.db.EntityMapper
import com.adidas.sports.goal.db.GoalDao
import com.adidas.sports.goal.exception.ServerException
import com.adidas.sports.goal.vo.GoalModel
import com.adidas.sports.goal.vo.ModelMapper
import javax.inject.Inject

@AppScope
class Repo @Inject constructor(private val api: AppApi,
                               private val dao: GoalDao,
                               private val application: BaseApplication): IRepo {

    private val userId = 5345435123L

    override suspend fun fetchGoals(): List<GoalModel> {
        try {

            if (NetworkUtils.isNetworkAvailable(application)){
                //cannot connect to this address in China
                //val result = api.getGoals().await()
                val result = BaseResponse<GoalResponse>()
                result.data?.items = listOf(
                        GoalDTO(1000, "Easy walk steps", "Walk 500 steps a day", "step", 500, RewardDTO(5, "bronze_medal")),
                        GoalDTO(1001, "Medium walk steps", "Walk 1000 steps a day", "step", 1000, RewardDTO(10, "silver_medal")),
                        GoalDTO(1002, "Hard walk steps", "Walk 6000 steps a day", "step", 6000, RewardDTO(20, "gold_medal")),
                        GoalDTO(1003, "Walk some distance", "Take a walk for 1 kilometer", "walking_distance", 1000, RewardDTO(5, "bronze_medal")),
                        GoalDTO(1004, "Quick Run", "Burn that donut by running 1 kilometer", "running_distance", 1000, RewardDTO(5, "silver_medal")),
                        GoalDTO(1005, "Medium Run", "Zombie apocalypse may come any day soon, be prepared for the occasion", "running_distance", 5000, RewardDTO(43, "zombie_hand"))
                )
                if (result.isOK){
                    dao.insertGoal(result.data!!.items.map { EntityMapper.from(it, userId) })
                    return dao.getGoalByUserId(userId).map { ModelMapper.from(it) }
                } else {
                    throw ServerException(result.code, result.message?:"")
                }
            } else {
                return dao.getGoalByUserId(userId).map { ModelMapper.from(it) }
            }

        } catch (e: Throwable) {
            throw ExceptionTransformer.error(e)
        }
    }
}
