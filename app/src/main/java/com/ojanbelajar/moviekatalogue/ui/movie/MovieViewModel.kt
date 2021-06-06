package com.ojanbelajar.moviekatalogue.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ojanbelajar.moviekatalogue.data.source.Repository
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.utils.Resource


class MovieViewModel @ViewModelInject constructor(
     val contentRepository: Repository
) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = contentRepository.getAllMovies()

    fun getMoviesByRate(): LiveData<Resource<PagedList<MovieEntity>>> = contentRepository.getAllMoviesByRate()

    fun getMoviesByPopularity(): LiveData<Resource<PagedList<MovieEntity>>> = contentRepository.getAllMoviesByPopularity()

}