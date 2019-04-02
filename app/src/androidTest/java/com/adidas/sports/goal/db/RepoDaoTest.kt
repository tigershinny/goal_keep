package com.adidas.sports.goal.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import io.reactivex.internal.util.NotificationLite.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndRead() = runBlocking {
        val repo = GoalEntity(1000, "Easy walk steps", "Walk 500 steps a day", "step", 500, RewardEntity(5, "bronze_medal"), 10001L)
        db.goalDao().insertGoal(repo)
        val loaded = getValue<List<GoalEntity>>(db.goalDao().getGoalById(1000))[0]
        assertThat(loaded, notNullValue())
        assertThat(loaded.id, `is`(1000L))
        assertThat(loaded.title, `is`("Easy walk steps"))
        assertThat(loaded.description, `is`("Walk 500 steps a day"))
        assertThat(loaded.type, `is`("step"))
        assertThat(loaded.goal, `is`(500))
        assertThat(loaded.reward.points, `is`(5))
        assertThat(loaded.reward.trophy, `is`("bronze_medal"))
    }
}
