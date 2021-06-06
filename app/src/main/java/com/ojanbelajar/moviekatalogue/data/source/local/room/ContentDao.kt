package com.ojanbelajar.moviekatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.room.*
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity

@Dao
interface ContentDao {

    @Query("Select * from movies")
    fun getMovies(): DataSource.Factory<Int,MovieEntity>

    @Query("Select * from movies order by vote_average desc")
    fun getMoviesByRate(): DataSource.Factory<Int,MovieEntity>

    @Query("Select * from movies order by popularity desc")
    fun getMoviesByPopularity(): DataSource.Factory<Int,MovieEntity>

    @Query("Select * from series")
    fun getSeries(): DataSource.Factory<Int,SeriesEntity>

    @Query("Select * from series order by vote_average desc")
    fun getSeriesByRate(): DataSource.Factory<Int,SeriesEntity>

    @Query("Select * from series order by popularity desc")
    fun getSeriesByPopularity(): DataSource.Factory<Int,SeriesEntity>

    @Query("Select * from movies where isFavorit = 1")
    fun getFavouriteMovies(): DataSource.Factory<Int,MovieEntity>

    @Query("Select * from series where isFavorit = 1")
    fun getFavouriteSeries(): DataSource.Factory<Int,SeriesEntity>

    @Query("Select * from movies where movieId = :id" )
    fun getMovieById(id: String):LiveData< MovieEntity>

    @Query("Select * from series where  seriesId= :id" )
    fun getSeriesById(id: String):LiveData< SeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = MovieEntity::class)
    fun insertMovies(movieEntities: List<MovieEntity>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = SeriesEntity::class)
    fun insertSeries(seriesEntities: List<SeriesEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movieEntity: MovieEntity)

    @Update(entity = SeriesEntity::class)
    fun updateSeries(seriesEntity: SeriesEntity)

}