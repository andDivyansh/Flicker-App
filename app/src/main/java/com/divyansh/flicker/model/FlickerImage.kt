package com.divyansh.flicker.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Divyansh Srivastava on 2019-10-11.
 *
 */
class FlickerImage(
    @SerializedName("id")
    var id: String,

    @SerializedName("owner")
    var owner: String,

    @SerializedName("secret")
     var secret: String,

    @SerializedName("server")
     var server: String,

    @SerializedName("farm")
     var farm: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("ispublic")
     var ispublic: String,

    @SerializedName("isfriend")
     var isfriend: String,

    @SerializedName("isfamily")
     var isfamily: String
)
