package com.adidas.sports.goal.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adidas.sports.goal.api.AppApi
import com.adidas.sports.goal.api.GoalResponse
import com.adidas.sports.goal.base.BaseApplication
import com.adidas.sports.goal.db.AppDatabase
import com.adidas.sports.goal.db.GoalDao
import com.adidas.sports.goal.db.GoalEntity
import com.adidas.sports.goal.db.RewardEntity
import com.adidas.sports.goal.net.BaseResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

@RunWith(JUnit4::class)
class RepoTest {
    private lateinit var repository: Repo
    private val dao = mock(GoalDao::class.java)
    private val service = mock(AppApi::class.java)
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = mock(AppDatabase::class.java)
        `when`(db.goalDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = Repo(service, dao, BaseApplication.instance)
    }

    @Test
    fun loadRepoFromNetwork() = runBlocking {
        val dbData = listOf<GoalEntity>()
        `when`(dao.getGoalById(10001L)).thenReturn(dbData)

        val goal = GoalEntity(1000,
                "Easy walk steps",
                "Walk 500 steps a day",
                "step",
                500,
                RewardEntity(5, "bronze_medal"),
                10001L)

        val call =  BaseResponse<GoalResponse>()
        `when`(service.getGoals().await()).thenReturn(call)

        val data = repository.fetchGoals()
        verify(dao).getGoalById(10001L)
        verifyNoMoreInteractions(service)
    }
}