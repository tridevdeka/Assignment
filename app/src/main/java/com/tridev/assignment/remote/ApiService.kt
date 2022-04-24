package com.tridev.assignment.remote

import com.tridev.assignment.remote.data.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/most-popular")
    suspend fun getAllShow(
        @Query("page") page: Int
    ): TvShowsResponse


}