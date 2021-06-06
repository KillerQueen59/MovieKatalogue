package com.ojanbelajar.moviekatalogue.data.source.remote

 import androidx.lifecycle.LiveData
 import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity


interface ContentRemoteSource {
    fun getAllMovies(): LiveData<ApiResponse<List<MovieEntity>>>

    fun getAllSeries(): LiveData<ApiResponse<List<SeriesEntity>>>

}