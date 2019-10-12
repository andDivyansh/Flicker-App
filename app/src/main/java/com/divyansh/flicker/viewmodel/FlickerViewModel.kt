package com.divyansh.flicker.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.divyansh.flicker.retrofit.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
class FlickerViewModel @Inject constructor(apiInterface: ApiService) : ViewModel() {

    var imageLinksData = MutableLiveData<List<String>>()
    private val imageList = mutableListOf<String>()
    private val apiService = apiInterface

    @SuppressLint("CheckResult")
    fun getFlickerImage() {

        apiService.getPublicPhotos()
            .flatMapIterable { publicPhotos ->
                Log.e(TAG, publicPhotos.photos?.photo.toString())
                publicPhotos.photos?.photo
            }.flatMap { photo ->
                Log.e(TAG, photo.toString())
                apiService.getImage(photo.id)
            }
            .parallel(20).sequential()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { imageLinksData.value = imageList }
            .subscribe(
                { it?.let { imageList.add(it.sizes.size[2].source) } },
                { Log.e(FlickerViewModel::class.java.simpleName, it.message) }

            )
    }

    companion object {
        val TAG: String = FlickerViewModel::class.java.simpleName
    }
}