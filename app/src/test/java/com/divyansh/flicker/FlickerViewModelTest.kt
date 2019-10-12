package com.divyansh.flicker

import com.divyansh.flicker.retrofit.ApiService
import com.divyansh.flicker.viewmodel.FlickerViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [23])
class FlickerViewModelTest : BaseTest(){

    private lateinit var flickerViewModel: FlickerViewModel
    private var service: ApiService? = null
    @Before
    override fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = createService(ApiService::class.java)
        flickerViewModel = FlickerViewModel(service!!)
    }

    @Test
    fun testGetFlickerImage(){
        enqueueResponse("testflicker.json")
        val photos = service?.getPublicPhotos()?.blockingFirst()
        Assert.assertNotNull( photos!!.photos)
    }
}