package com.tridev.assignment.remote.data

import com.google.gson.annotations.SerializedName

data class TvShows(
    @SerializedName("image_thumbnail_path")
    val url: String,

    @SerializedName("name")
    val title: String,

    @SerializedName("network")
    val network: String? = null

)
