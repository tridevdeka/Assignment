package com.tridev.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tridev.assignment.viewmodel.MainViewModel
import com.tridev.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mAdapter: TvShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()



        lifecycleScope.launch {
            mainViewModel.getAllUsers.collectLatest { pagingData ->
                binding.rvRemote.isVisible = true
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun initialization() {
        mAdapter = TvShowsAdapter()
        binding.rvRemote.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 1)
            adapter = mAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter { mAdapter.retry() }
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    binding.progress.isVisible = false
                }
            })
        }
    }
}

