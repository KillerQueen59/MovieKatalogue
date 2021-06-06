  package com.ojanbelajar.moviekatalogue.ui.series

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.databinding.ItemContentBinding
import com.ojanbelajar.moviekatalogue.movie.MovieNowPlayingAdapter
import com.ojanbelajar.moviekatalogue.movie.NowPlayingViewHolder
import com.ojanbelajar.moviekatalogue.ui.detail.DetailContentActivity
import com.ojanbelajar.moviekatalogue.ui.series.SeriesOnAiringAdapter.Companion.DIFF_CALLBACK
import org.jetbrains.anko.startActivity

class SeriesOnAiringAdapter(private val context: Context): PagedListAdapter<SeriesEntity, OnAiringViewHolder>(DIFF_CALLBACK) {
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SeriesEntity>(){
            override fun areItemsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnAiringViewHolder = OnAiringViewHolder(
        ItemContentBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: OnAiringViewHolder, position: Int) {
        val onAiring = getItem(position)
        if (onAiring != null){
            holder.bind(onAiring)
            holder.itemView.setOnClickListener {
                context.startActivity<DetailContentActivity>("id" to onAiring.id , "type" to "series")
            }
        }
    }
}

class OnAiringViewHolder (private val itemBinding: ItemContentBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(detail: SeriesEntity){
        itemBinding.rate.text = detail.vote_average.toString()
        itemBinding.progressBar.visibility = View.VISIBLE
        itemBinding.rate.visibility = View.GONE
        itemBinding.star.visibility = View.GONE
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/original/"+detail.poster_path)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemBinding.rate.visibility = View.VISIBLE
                    itemBinding.star.visibility = View.VISIBLE
                    itemBinding.progressBar.visibility = View.GONE
                    return false
                }

            })
            .into(itemBinding.cardImage)
    }
}