package com.ojanbelajar.moviekatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.data.source.local.room.ContentDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: ContentDao
): ContentLocalSource{

    override fun getAllMovies(): DataSource.Factory<Int,MovieEntity> = dao.getMovies()

    override fun getAllMoviesByRate(): DataSource.Factory<Int, MovieEntity> = dao.getMoviesByRate()

    override fun getAllMoviesByPopularity(): DataSource.Factory<Int, MovieEntity> = dao.getMoviesByPopularity()

    override fun getAllSeries(): DataSource.Factory<Int, SeriesEntity> = dao.getSeries()

    override fun getAllSeriesByRate(): DataSource.Factory<Int, SeriesEntity> = dao.getSeriesByRate()

    override fun getAllSeriesByPopularity(): DataSource.Factory<Int, SeriesEntity> = dao.getSeriesByPopularity()

    override fun getFavouriteMovies(): DataSource.Factory<Int, MovieEntity> = dao.getFavouriteMovies()

    override fun getFavouriteSeries(): DataSource.Factory<Int, SeriesEntity> = dao.getFavouriteSeries()

    override fun getMovieById(id: String): LiveData<MovieEntity> = dao.getMovieById(id)

    override fun getSeriesById(id: String): LiveData<SeriesEntity> = dao.getSeriesById(id)

    override fun updateMovie(movieEntity: MovieEntity) {
        movieEntity.isFavorite = !movieEntity.isFavorite
        dao.updateMovie(movieEntity)
    }

    override fun updateSeries(seriesEntity: SeriesEntity) {
        seriesEntity.isFavorite = !seriesEntity.isFavorite
        dao.updateSeries(seriesEntity)
    }

    override fun insertMovie(movieEntity: List<MovieEntity>) = dao.insertMovies(movieEntity)

    override fun insertSeries(seriesEntity: List<SeriesEntity>) = dao.insertSeries(seriesEntity)

}