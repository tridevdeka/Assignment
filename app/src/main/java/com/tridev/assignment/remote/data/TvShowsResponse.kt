package com.tridev.assignment.remote.data

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(

    @SerializedName("tv_shows")
    val data: List<TvShows>
){

}
