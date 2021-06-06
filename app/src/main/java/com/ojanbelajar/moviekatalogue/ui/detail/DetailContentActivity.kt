package com.ojanbelajar.moviekatalogue.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ojanbelajar.moviekatalogue.R
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.databinding.ActivityDetailContentBinding
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast

@AndroidEntryPoint
class DetailContentActivity: AppCompatActivity() {

    private lateinit var binding : ActivityDetailContentBinding
    private val model : DetailContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data : String = intent.getStringExtra("id") ?: ""
        model.setSelectedContent(data)
        init()
        when(intent.getStringExtra("type") ?: ""){
            "movie" -> {
                model.getContentMovies().observe(this, Observer {
                    binding.progressBar.visibility = View.GONE
                    setDataMovie(it)
                    initFavourite(it.isFavorite)
                })
            }
            "series" -> {
                model.getContentSeries().observe(this, Observer {
                    binding.progressBar.visibility = View.GONE
                    setDataSeries(it)
                    initFavourite(it.isFavorite)
                })
            }
        }
    }

    private fun init() {
        binding.rvCast.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvCast.setHasFixedSize(true)
    }

    @SuppressLint("SetTextI18n")
    private fun setDataMovie(detail: MovieEntity?) {
        binding.titleDetail.text = detail?.original_title
        binding.dateDetail.text = "Release Date: ${detail?.release_date}"
        binding.detailDetail.text = detail?.overview
        binding.rate.text = detail?.vote_average.toString()
        Glide.with(this).load("https://image.tmdb.org/t/p/original/"+detail?.backdrop_path).into(binding.backdropDetail)
        Glide.with(this).load("https://image.tmdb.org/t/p/original/"+detail?.poster_path).into(binding.posterDetail)
        binding.btnFavourite.setOnClickListener {
            setFavouriteMovie(detail)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDataSeries(detail: SeriesEntity?) {
        binding.titleDetail.text = detail?.original_name
        binding.dateDetail.text = "Release Date: ${detail?.first_air_date}"
        binding.detailDetail.text = detail?.overview
        binding.rate.text = detail?.vote_average.toString()
        Glide.with(this).load("https://image.tmdb.org/t/p/original/"+detail?.backdrop_path).into(binding.backdropDetail)
        Glide.with(this).load("https://image.tmdb.org/t/p/original/"+detail?.poster_path).into(binding.posterDetail)
        binding.btnFavourite.setOnClickListener {
            setFavouriteSeries(detail)
        }
    }

   private fun initFavourite(check: Boolean){
        if (check){
            binding.btnFavourite.background = ContextCompat.getDrawable(applicationContext, R.drawable.round_fav)
            binding.btnFavourite.textColor = ContextCompat.getColor(applicationContext,R.color.white)
        } else {
            binding.btnFavourite.background = ContextCompat.getDrawable(applicationContext, R.drawable.round_fav_border)
            binding.btnFavourite.textColor = ContextCompat.getColor(applicationContext,R.color.yellow)
        }
    }

    private fun setFavouriteMovie(movieEntity: MovieEntity?){
        if (movieEntity != null){
            if (movieEntity.isFavorite){
                toast("Removed from favourite")
            } else {
                toast("Add to favourite")
            }
            model.setFavouriteMovies(movieEntity)
        }
    }

    private fun setFavouriteSeries(seriesEntity: SeriesEntity?){
        if (seriesEntity != null){
            if (seriesEntity.isFavorite){
                toast("Removed from favourite")
            } else {
                toast("Add to favourite")
            }
            model.setFavouriteSeries(seriesEntity)
        }
    }

}