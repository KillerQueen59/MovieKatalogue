package com.ojanbelajar.moviekatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ojanbelajar.moviekatalogue.api.ApiService
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class RemoteRemoteSource @Inject constructor(
    private val apiService: ApiService
): ContentRemoteSource {

    override  fun  getAllMovies(): LiveData<ApiResponse<List<MovieEntity>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieEntity>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getMovies().await()
                resultMovieResponse.postValue(ApiResponse.success(response.results))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }

    override  fun getAllSeries(): LiveData<ApiResponse<List<SeriesEntity>>> {
        EspressoIdlingResource.increment()
        val resultSeriesResponse = MutableLiveData<ApiResponse<List<SeriesEntity>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getSeries().await()
                resultSeriesResponse.postValue(ApiResponse.success(response.results))
            } catch (e: IOException) {
                e.printStackTrace()
                resultSeriesResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultSeriesResponse
    }


}