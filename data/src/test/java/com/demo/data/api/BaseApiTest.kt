package com.demo.data.api

import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

abstract class BaseApiTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    protected lateinit var testRetrofit: Retrofit
    protected val baseUrl = "www.baseUrl.com/"

    private val mockWebServer = MockWebServer()

    @Before
    fun startServer() {
        mockWebServer.start()
        testRetrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    protected fun enqueueResponse(body: Any) {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(Gson().toJson(body))
        )
    }

    protected fun takeRequest() = mockWebServer.takeRequest()
}