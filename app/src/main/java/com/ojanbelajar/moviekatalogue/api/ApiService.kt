package com.ojanbelajar.moviekatalogue.api

import com.ojanbelajar.moviekatalogue.BuildConfig
import com.ojanbelajar.moviekatalogue.data.source.remote.response.MovieResponse
import com.ojanbelajar.moviekatalogue.data.source.remote.response.SeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") api: String = BuildConfig.API_KEY
    ): Call<MovieResponse>

    @GET("tv/on_the_air")
    fun getSeries(
        @Query("api_key") api: String = BuildConfig.API_KEY
    ): Call<SeriesResponse>

}