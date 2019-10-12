package com.divyansh.flicker

import android.net.Uri
import androidx.lifecycle.LifecycleOwner
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.BufferedSource
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.mockito.Mockito
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
@Config(manifest = Config.NONE, sdk = [23])
open class BaseTest {
    protected val webServer = MockWebServer()
    protected lateinit var baseUri: Uri

    companion object {
        const val BASE_URI_PATH = "/test/path/v0_1"
    }

    @Before
    open fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        webServer.start()
        val url = webServer.url(BASE_URI_PATH).toString()
        baseUri = Uri.parse(url)
    }

    @After
    open fun shutDown() {
        webServer.shutdown()
    }


    @Throws(IOException::class)
    fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }


    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream =
            BaseTest::class.java.getClassLoader()?.getResourceAsStream(String.format("json.valid/response/%s", fileName))
        val source = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        webServer.enqueue(mockResponse.setBody((source as BufferedSource).readString(StandardCharsets.UTF_8)))
    }

    fun <T> createService(clazz: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(webServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(clazz)
    }
}