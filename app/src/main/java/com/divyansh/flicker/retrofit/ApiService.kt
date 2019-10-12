package com.divyansh.flicker.retrofit

import com.divyansh.flicker.model.ImageResult
import com.divyansh.flicker.model.ImageUrls
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
interface ApiService {

    @GET("rest/?method=flickr.people.getPublicPhotos&api_key=" + ApiUtil.apiKey + "&user_id=135487628%40N06&format=json&nojsoncallback=1")
    fun getPublicPhotos(): Flowable<ImageResult>

    @GET("rest/?method=flickr.photos.getSizes&api_key=" + ApiUtil.apiKey + "&user_id=135487628%40N06&format=json&nojsoncallback=1")
    fun getImage(@Query("photo_id") photo_id: String): Flowable<ImageUrls>

}