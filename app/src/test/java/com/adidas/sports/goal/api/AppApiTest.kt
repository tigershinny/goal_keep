package com.adidas.sports.goal.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adidas.sports.goal.BaseConfig.APP_URL
import com.adidas.sports.goal.net.BaseResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.internal.util.NotificationLite.getValue
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient



@RunWith(JUnit4::class)
class AppApiTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: AppApi

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()

        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url(APP_URL))
                .client(OkHttpClient.Builder().build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getGoals() = runBlocking {
        enqueueResponse("goals.json")
        val response = getValue(service.getGoals().await()) as BaseResponse<GoalResponse>
        val goals = response.data?.items
        val request = mockWebServer.takeRequest()
        assertThat(request.path, `is`("/_ah/api/myApi/v1/goals"))

        assertThat<List<GoalDTO>>(goals, notNullValue())
        val goal = goals?.get(0)
        assertThat(goal?.type, `is`("step"))
        assertThat(goal?.title, `is`("Easy walk steps"))
        assertThat(goal?.goal, `is`(500))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
                mockResponse
                        .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
