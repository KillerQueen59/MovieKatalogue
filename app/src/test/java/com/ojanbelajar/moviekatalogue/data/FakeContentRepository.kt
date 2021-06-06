package com.ojanbelajar.moviekatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ojanbelajar.moviekatalogue.data.source.NetworkBoundResource
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.data.source.remote.ContentRemoteSource
import com.ojanbelajar.moviekatalogue.data.source.Repository
import com.ojanbelajar.moviekatalogue.data.source.local.ContentLocalSource
import com.ojanbelajar.moviekatalogue.data.source.remote.ApiResponse
import com.ojanbelajar.moviekatalogue.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class FakeContentRepository  constructor(
        private val contentRemoteSource: ContentRemoteSource,
        private val contentLocalSource: ContentLocalSource
): Repository
{
    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieEntity>>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(contentLocalSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> =
                contentRemoteSource.getAllMovies()

            public override fun saveCallResult(data: List<MovieEntity>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data ) {
                    val course = MovieEntity(response.id,
                        response.original_title,
                        response.overview,
                        response.popularity,
                        response.release_date,
                        response.vote_average,
                        response.poster_path,
                        response.backdrop_path,
                        false
                    )
                    movieList.add(course)
                }

                contentLocalSource.insertMovie(movieList)
            }

        }.asLiveData()

    }

    override fun getAllMoviesByRate(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieEntity>>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(contentLocalSource.getAllMoviesByRate(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> =
                contentRemoteSource.getAllMovies()

            public override fun saveCallResult(data: List<MovieEntity>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data ) {
                    val course = MovieEntity(response.id,
                        response.original_title,
                        response.overview,
                        response.popularity,
                        response.release_date,
                        response.vote_average,
                        response.poster_path,
                        response.backdrop_path,
                        false
                    )
                    movieList.add(course)
                }

                contentLocalSource.insertMovie(movieList)
            }

        }.asLiveData()
    }

    override fun getAllMoviesByPopularity(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieEntity>>() {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(contentLocalSource.getAllMoviesByPopularity(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            public override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> =
                contentRemoteSource.getAllMovies()

            public override fun saveCallResult(data: List<MovieEntity>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data ) {
                    val course = MovieEntity(response.id,
                        response.original_title,
                        response.overview,
                        response.popularity,
                        response.release_date,
                        response.vote_average,
                        response.poster_path,
                        response.backdrop_path,
                        false
                    )
                    movieList.add(course)
                }

                contentLocalSource.insertMovie(movieList)
            }

        }.asLiveData()
    }

    override  fun getAllSeries(): LiveData<Resource<PagedList<SeriesEntity>>> {
        return object : NetworkBoundResource<PagedList<SeriesEntity>, List<SeriesEntity>>() {
            public override fun loadFromDB(): LiveData<PagedList<SeriesEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(contentLocalSource.getAllSeries(), config).build()
            }

            override fun shouldFetch(data: PagedList<SeriesEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<SeriesEntity>>> =
                contentRemoteSource.getAllSeries()

            public override fun saveCallResult(data: List<SeriesEntity>) {
                val seriesList = ArrayList<SeriesEntity>()
                for (response in data ) {
                    val course = SeriesEntity(response.id,
                        response.original_name,
                        response.overview,
                        response.popularity,
                        response.first_air_date,
                        response.vote_average,
                        response.poster_path,
                        response.backdrop_path,
                        false)
                    seriesList.add(course)
                }
                contentLocalSource.insertSeries(seriesList)
            }
        }.asLiveData()

    }

    override fun getAllSeriesByRate(): LiveData<Resource<PagedList<SeriesEntity>>> {
        return object : NetworkBoundResource<PagedList<SeriesEntity>, List<SeriesEntity>>() {
            public override fun loadFromDB(): LiveData<PagedList<SeriesEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(contentLocalSource.getAllSeriesByRate(), config).build()
            }

            override fun shouldFetch(data: PagedList<SeriesEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<SeriesEntity>>> =
                contentRemoteSource.getAllSeries()

            public override fun saveCallResult(data: List<SeriesEntity>) {
                val seriesList = ArrayList<SeriesEntity>()
                for (response in data ) {
                    val course = SeriesEntity(response.id,
                        response.original_name,
                        response.overview,
                        response.popularity,
                        response.first_air_date,
                        response.vote_average,
                        response.poster_path,
                        response.backdrop_path,
                        false)
                    seriesList.add(course)
                }
                contentLocalSource.insertSeries(seriesList)
            }
        }.asLiveData()
    }

    override fun getAllSeriesByPopularity(): LiveData<Resource<PagedList<SeriesEntity>>> {
        return object : NetworkBoundResource<PagedList<SeriesEntity>, List<SeriesEntity>>() {
            public override fun loadFromDB(): LiveData<PagedList<SeriesEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(contentLocalSource.getAllSeriesByPopularity(), config).build()
            }

            override fun shouldFetch(data: PagedList<SeriesEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<SeriesEntity>>> =
                contentRemoteSource.getAllSeries()

            public override fun saveCallResult(data: List<SeriesEntity>) {
                val seriesList = ArrayList<SeriesEntity>()
                for (response in data ) {
                    val course = SeriesEntity(response.id,
                        response.original_name,
                        response.overview,
                        response.popularity,
                        response.first_air_date,
                        response.vote_average,
                        response.poster_path,
                        response.backdrop_path,
                        false)
                    seriesList.add(course)
                }
                contentLocalSource.insertSeries(seriesList)
            }
        }.asLiveData()
    }

    override fun getFavouriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(contentLocalSource.getFavouriteMovies(),config).build()
    }

    override fun getFavouriteSeries(): LiveData<PagedList<SeriesEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(contentLocalSource.getFavouriteSeries(),config).build()
    }

    override fun getMovieId(id: String): LiveData<MovieEntity> = contentLocalSource.getMovieById(id)

    override fun getSeriesId(id: String): LiveData<SeriesEntity> = contentLocalSource.getSeriesById(id)

    override fun setFavouriteMovies(movieEntity: MovieEntity) {
        CoroutineScope(IO).launch {
            contentLocalSource.updateMovie(movieEntity)
        }
    }

    override fun setFavouriteSeries(seriesEntity: SeriesEntity) {
        CoroutineScope(IO).launch {
            contentLocalSource.updateSeries(seriesEntity)
        }
    }
}