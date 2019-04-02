package com.adidas.sports.goal.api

import com.adidas.sports.goal.net.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface AppApi {

    /**
     *  指定时间段，每天课程状态信息
     */
    @POST(Path.GOALS)
    fun getGoals(): Deferred<BaseResponse<GoalResponse>>
}