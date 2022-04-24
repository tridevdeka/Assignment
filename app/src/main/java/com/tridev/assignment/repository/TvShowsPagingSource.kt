package com.tridev.assignment.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tridev.assignment.remote.data.TvShows
import com.tridev.assignment.remote.ApiService
import retrofit2.HttpException
import java.io.IOException

class TvShowsPagingSource constructor(private val apiService: ApiService) :
    PagingSource<Int, TvShows>() {
    override fun getRefreshKey(state: PagingState<Int, TvShows>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShows> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getAllShow(page)
            val users = response.data
            Log.e("PAGE-->>", page.toString())

            LoadResult.Page(
                data = users,
                prevKey = null,
                nextKey = if (users.isEmpty()) null else (page + 1)
            )
        } catch (
            e: IOException
        ) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}