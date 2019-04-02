package com.adidas.sports.goal.db

import androidx.room.*

@Dao
interface GoalDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(entity: GoalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(entity: List<GoalEntity>)

    @Update
    suspend fun updateGoal(entity: GoalEntity)

    @Delete
    suspend fun deleteGoal(entity: GoalEntity)

    @Query("SELECT * FROM GoalEntity WHERE userId == :userId")
    fun getGoalByUserId(userId: Long): List<GoalEntity>

    @Query("SELECT * FROM GoalEntity WHERE id == :id")
    fun getGoalById(id: Long): List<GoalEntity>
}