package com.ojanbelajar.moviekatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.ojanbelajar.moviekatalogue.CoroutinesTestRule
import com.ojanbelajar.moviekatalogue.DummyContentTest
import com.ojanbelajar.moviekatalogue.LiveDataTestUtil
import com.ojanbelajar.moviekatalogue.PagedListUtil
import com.ojanbelajar.moviekatalogue.data.source.local.ContentLocalSource
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.data.source.remote.ContentRemoteSource
import com.ojanbelajar.moviekatalogue.utils.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class ContentRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()
    private val remote = mock(ContentRemoteSource::class.java)
    private val local = mock(ContentLocalSource::class.java)
    private val contentRepository = FakeContentRepository(remote,local)

    private val listMovie = DummyContentTest.getMoviesDummy<ContentRepositoryTest>()
    private val listSeries = DummyContentTest.getSeriesDummy<ContentRepositoryTest>()
    private val movie = DummyContentTest.getMovieDummyId<ContentRepositoryTest>()
    private val series = DummyContentTest.getSeriesDummyId<ContentRepositoryTest>()

    @Test
    fun getAllMovies() = runBlocking {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSource)
        contentRepository.getAllMovies()
        val result = Resource.success(PagedListUtil.mockPagedList(DummyContentTest.getMoviesDummy<ContentRepositoryTest>()))
        verify(local).getAllMovies()
        assertNotNull(result.data)
        assertEquals(listMovie.size.toLong(),result.data?.size?.toLong())
    }

    @Test
    fun getAllMoviesByRate() = runBlocking {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getAllMoviesByRate()).thenReturn(dataSource)
        contentRepository.getAllMoviesByRate()
        val result = Resource.success(PagedListUtil.mockPagedList(DummyContentTest.getMoviesDummy<ContentRepositoryTest>()))
        verify(local).getAllMoviesByRate()
        assertNotNull(result.data)
        assertEquals(listMovie.size.toLong(),result.data?.size?.toLong())
    }

    @Test
    fun getAllMoviesByPopularity() = runBlocking {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getAllMoviesByPopularity()).thenReturn(dataSource)
        contentRepository.getAllMoviesByPopularity()
        val result = Resource.success(PagedListUtil.mockPagedList(DummyContentTest.getMoviesDummy<ContentRepositoryTest>()))
        verify(local).getAllMoviesByPopularity()
        assertNotNull(result.data)
        assertEquals(listMovie.size.toLong(),result.data?.size?.toLong())
    }


    @Test
    fun getAllSeries() = runBlocking {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,SeriesEntity>
        `when`(local.getAllSeries()).thenReturn(dataSource)
        contentRepository.getAllSeries()
        val result = Resource.success(PagedListUtil.mockPagedList(DummyContentTest.getSeriesDummy<ContentRepositoryTest>()))
        verify(local).getAllSeries()
        assertNotNull(result.data)
        assertEquals(listSeries.size.toLong(),result.data?.size?.toLong())
    }

    @Test
    fun getAllSeriesByRate() = runBlocking {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,SeriesEntity>
        `when`(local.getAllSeriesByRate()).thenReturn(dataSource)
        contentRepository.getAllSeriesByRate()
        val result = Resource.success(PagedListUtil.mockPagedList(DummyContentTest.getSeriesDummy<ContentRepositoryTest>()))
        verify(local).getAllSeriesByRate()
        assertNotNull(result.data)
        assertEquals(listSeries.size.toLong(),result.data?.size?.toLong())
    }

    @Test
    fun getAllSeriesByPopularity() = runBlocking {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,SeriesEntity>
        `when`(local.getAllSeriesByPopularity()).thenReturn(dataSource)
        contentRepository.getAllSeriesByPopularity()
        val result = Resource.success(PagedListUtil.mockPagedList(DummyContentTest.getSeriesDummy<ContentRepositoryTest>()))
        verify(local).getAllSeriesByPopularity()
        assertNotNull(result.data)
        assertEquals(listSeries.size.toLong(),result.data?.size?.toLong())
    }

    @Test
    fun getFavouriteMovies() = runBlocking {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getFavouriteMovies()).thenReturn(dataSource)
        contentRepository.getFavouriteMovies()
        val result = Resource.success(PagedListUtil.mockPagedList(DummyContentTest.getMoviesDummy<ContentRepositoryTest>()))
        verify(local).getFavouriteMovies()
        assertNotNull(result.data)
        assertEquals(listMovie.size.toLong(), result.data?.size?.toLong())
    }

    @Test
    fun getFavouriteSeries() = runBlocking {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,SeriesEntity>
        `when`(local.getFavouriteSeries()).thenReturn(dataSource)
        contentRepository.getFavouriteSeries()
        val result = Resource.success(PagedListUtil.mockPagedList(DummyContentTest.getSeriesDummy<ContentRepositoryTest>()))
        verify(local).getFavouriteSeries()
        assertNotNull(result.data)
        assertEquals(listMovie.size.toLong(), result.data?.size?.toLong())
    }

    @Test
    fun getMovieId() = runBlocking {
        val dataSource = MutableLiveData<MovieEntity>()
        dataSource.value = movie
        `when`(local.getMovieById(movie.id)).thenReturn(dataSource)
        val result = LiveDataTestUtil.getValue(contentRepository.getMovieId(movie.id))
        verify(local).getMovieById(movie.id)
        assertNotNull(result)
        assertEquals(movie.id,result.id)
    }

    @Test
    fun getSeriesId() = runBlocking {
        val dataSource = MutableLiveData<SeriesEntity>()
        dataSource.value = series
        `when`(local.getSeriesById(series.id)).thenReturn(dataSource)
        val result = LiveDataTestUtil.getValue(contentRepository.getSeriesId(series.id))
        verify(local).getSeriesById(series.id)
        assertNotNull(result)
        assertEquals(series.id,result.id)
    }

    @Test
    fun setFavouriteMovies() = runBlocking {
        val dataSource = MutableLiveData<MovieEntity>()
        dataSource.value = movie
        `when`(local.getMovieById(movie.id)).thenReturn(dataSource)
        contentRepository.setFavouriteMovies(movie)
        verify(local, times(1)).updateMovie(movie)
    }

    @Test
    fun setFavouriteSeries() = runBlocking {
        val dataSource = MutableLiveData<SeriesEntity>()
        dataSource.value = series
        `when`(local.getSeriesById(series.id)).thenReturn(dataSource)
        contentRepository.setFavouriteSeries(series)
        verify(local, times(1)).updateSeries(series)
    }



}