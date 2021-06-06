package com.ojanbelajar.moviekatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity

interface ContentLocalSource {

    fun getAllMovies(): DataSource.Factory<Int,MovieEntity>

    fun getAllMoviesByRate(): DataSource.Factory<Int,MovieEntity>

    fun getAllMoviesByPopularity(): DataSource.Factory<Int,MovieEntity>

    fun getAllSeries(): DataSource.Factory<Int,SeriesEntity>

    fun getAllSeriesByRate(): DataSource.Factory<Int,SeriesEntity>

    fun getAllSeriesByPopularity(): DataSource.Factory<Int,SeriesEntity>

    fun getFavouriteMovies(): DataSource.Factory<Int,MovieEntity>

    fun getFavouriteSeries(): DataSource.Factory<Int,SeriesEntity>

    fun getMovieById(id: String): LiveData<MovieEntity>

    fun getSeriesById(id: String): LiveData<SeriesEntity>

    fun updateMovie(movieEntity: MovieEntity)

    fun updateSeries(seriesEntity: SeriesEntity)

    fun insertMovie(movieEntity: List<MovieEntity>)

    fun insertSeries(seriesEntity: List<SeriesEntity>)


}