package com.ojanbelajar.moviekatalogue.ui.favourite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ojanbelajar.moviekatalogue.data.source.Repository
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity

class FavouriteViewModel @ViewModelInject constructor(
    private val contentRepository: Repository
) : ViewModel() {
    fun getFavouriteMovie(): LiveData<PagedList<MovieEntity>> = contentRepository.getFavouriteMovies()

    fun getFavouriteSeries(): LiveData<PagedList<SeriesEntity>> = contentRepository.getFavouriteSeries()

}