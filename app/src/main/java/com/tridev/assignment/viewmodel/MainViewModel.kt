package com.tridev.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tridev.assignment.repository.TvShowsPagingSource
import com.tridev.assignment.remote.data.TvShows
import com.tridev.assignment.remote.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val apiService: ApiService) : ViewModel() {

    val getAllUsers: Flow<PagingData<TvShows>> = Pager(
        config = PagingConfig(5,enablePlaceholders = false)
    ) {
        TvShowsPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}
