package com.ojanbelajar.moviekatalogue.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.data.source.Repository


class DetailContentViewModel @ViewModelInject constructor(
        private val contentRepository: Repository
) : ViewModel() {

    private lateinit var contentId: String

    fun setSelectedContent(contentId: String){
        this.contentId = contentId
    }

    fun getContentMovies(): LiveData<MovieEntity> = contentRepository.getMovieId(contentId)

    fun getContentSeries(): LiveData<SeriesEntity> = contentRepository.getSeriesId(contentId)

    fun setFavouriteMovies(movieEntity: MovieEntity){
        contentRepository.setFavouriteMovies(movieEntity)
    }

    fun setFavouriteSeries(seriesEntity: SeriesEntity){
        contentRepository.setFavouriteSeries(seriesEntity)
    }

}