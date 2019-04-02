package com.adidas.sports.goal.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import io.reactivex.internal.util.NotificationLite.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GoalDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testInsertAndLoad() = runBlocking {
        val goal = GoalEntity(1000, "Easy walk steps", "Walk 500 steps a day", "step", 500, RewardEntity(5, "bronze_medal"), 10001L)
        db.goalDao().insertGoal(goal)

        val loaded = getValue<List<GoalEntity>>(db.goalDao().getGoalById(10001L))
        assertThat(loaded[0].id, `is`(1000L))

        val replacement = GoalEntity(1000, "Medium walk steps", "Walk 1000 steps a day", "step", 1000, RewardEntity(10, "silver_medal"), 10001L)
        db.goalDao().insertGoal(replacement)

        val loadedReplacement =  getValue<List<GoalEntity>>(db.goalDao().getGoalById(10001L))
        assertThat(loadedReplacement[0].id, `is`(1000L))
    }
}
