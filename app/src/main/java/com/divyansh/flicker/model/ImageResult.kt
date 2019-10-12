package com.divyansh.flicker.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
class ImageResult {

    @SerializedName("photos")
    var photos: Result? = null

    inner class Result {
        @SerializedName("photo")
        var photo: List<FlickerImage>? = null
    }
}