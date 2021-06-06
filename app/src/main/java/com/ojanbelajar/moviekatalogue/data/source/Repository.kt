package com.ojanbelajar.moviekatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.utils.Resource

interface Repository {
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllMoviesByRate(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllMoviesByPopularity(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllSeries(): LiveData<Resource<PagedList<SeriesEntity>>>

    fun getAllSeriesByRate():  LiveData<Resource<PagedList<SeriesEntity>>>

    fun getAllSeriesByPopularity():  LiveData<Resource<PagedList<SeriesEntity>>>

    fun getFavouriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavouriteSeries(): LiveData<PagedList<SeriesEntity>>

    fun getMovieId(id: String): LiveData<MovieEntity>

    fun getSeriesId(id: String): LiveData<SeriesEntity>

    fun setFavouriteMovies(movieEntity: MovieEntity)

    fun setFavouriteSeries(seriesEntity: SeriesEntity)

}