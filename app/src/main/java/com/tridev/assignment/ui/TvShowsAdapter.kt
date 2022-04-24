package com.tridev.assignment.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tridev.assignment.remote.data.TvShows
import com.tridev.assignment.util.Constants.PREF_KEY
import com.tridev.assignment.util.Constants.TYPE_AD
import com.tridev.assignment.util.Constants.TYPE_REMOTE
import com.tridev.assignment.databinding.NativeAdLayoutBinding
import com.tridev.assignment.databinding.TvShowsItemContainerBinding
import com.tridev.assignment.util.Constants

class TvShowsAdapter :
    PagingDataAdapter<TvShows, RecyclerView.ViewHolder>(Diff()) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == TYPE_AD) {
            (holder as AdViewHolder).bind()
        } else {
            getItem(position)?.let {
                (holder as TvShowsViewHolder).bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TYPE_AD) {
            val binding =
                NativeAdLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdViewHolder(binding)
        } else {
            TvShowsViewHolder(
                TvShowsItemContainerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }


    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 10 && Constants.getIntPrefValue(PREF_KEY, 0) == 0) {
            TYPE_AD
        } else {
            TYPE_REMOTE
        }
    }


    class TvShowsViewHolder(private val binding: TvShowsItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShows: TvShows) {
            binding.apply {
                ivTvShows.load(tvShows.url)
                tvTitle.text=tvShows.title
                tvNetwork.text=tvShows.network
            }
        }
    }

    inner class AdViewHolder(private val mBinding: NativeAdLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind() {
            mBinding.ivClose.setOnClickListener {
                mBinding.cvAdContainer.visibility = View.GONE
                Constants.saveIntPrefValue(PREF_KEY, 1)
            }
        }
    }

    class Diff : DiffUtil.ItemCallback<TvShows>() {
        override fun areItemsTheSame(oldItem: TvShows, newItem: TvShows): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: TvShows, newItem: TvShows): Boolean =
            oldItem == newItem

    }
}
