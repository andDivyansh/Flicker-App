package com.divyansh.flicker.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
data class ImageUrls(
    @SerializedName("sizes")
    val sizes: Sizes,
    @SerializedName("stat")
    val stat: String
)