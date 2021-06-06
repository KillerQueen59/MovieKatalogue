package com.ojanbelajar.moviekatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.ojanbelajar.moviekatalogue.CoroutinesTestRule
import com.ojanbelajar.moviekatalogue.DummyContentTest
import com.ojanbelajar.moviekatalogue.data.FakeContentRepository
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailContentViewModelTest {

    private lateinit var model: DetailContentViewModel

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()
    @get:Rule
    val coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()
    @Mock
    private lateinit var detailRepository: FakeContentRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerSeries: Observer<SeriesEntity>

    @Before
    fun setup(){
        model = DetailContentViewModel(detailRepository)
    }


    @Test
    fun getContentMovies() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val dummyMovies = DummyContentTest.getMovieDummyId<DetailContentViewModelTest>()
            val movieId = dummyMovies.id
            val movies = MutableLiveData<MovieEntity>()
            movies.value = dummyMovies

            Mockito.`when`(detailRepository.getMovieId(movieId)).thenReturn(movies)
            model.setSelectedContent(movieId)
            val result = model.getContentMovies().value
            assertNotNull(result)
            assertEquals(dummyMovies.id,result?.id)
            assertEquals(dummyMovies.original_title,result?.original_title)
            assertEquals(dummyMovies.release_date,result?.release_date)
            assertEquals(dummyMovies.popularity,result?.popularity)
            assertEquals(dummyMovies.backdrop_path,result?.backdrop_path)
            assertEquals(dummyMovies.poster_path,result?.poster_path)
            assertEquals(dummyMovies.overview,result?.overview)
            model.getContentMovies().observeForever(observerMovie)
            verify(observerMovie).onChanged(dummyMovies)
        }
    }

    @Test
    fun getContentSeries() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val dummySeries = DummyContentTest.getSeriesDummyId<DetailContentViewModelTest>()
            val seriesId = dummySeries.id
            val series = MutableLiveData<SeriesEntity>()
            series.value = dummySeries
            Mockito.`when`(detailRepository.getSeriesId(seriesId)).thenReturn(series)
            model.setSelectedContent(seriesId)
            val result = model.getContentSeries().value
            assertNotNull(result)
            assertEquals(dummySeries.id,result?.id)
            assertEquals(dummySeries.original_name,result?.original_name)
            assertEquals(dummySeries.first_air_date,result?.first_air_date)
            assertEquals(dummySeries.popularity,result?.popularity)
            assertEquals(dummySeries.backdrop_path,result?.backdrop_path)
            assertEquals(dummySeries.poster_path,result?.poster_path)
            assertEquals(dummySeries.overview,result?.overview)
            model.getContentSeries().observeForever(observerSeries)
            verify(observerSeries).onChanged(dummySeries)
        }
    }

    @Test
    fun setFavouriteMovie(){
        val dummyMovie = DummyContentTest.getMovieDummyId<DetailContentViewModelTest>()
        val dummyId = dummyMovie.id
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie
        Mockito.`when`(detailRepository.getMovieId(dummyId)).thenReturn(movie)
        model.setFavouriteMovies(dummyMovie)
        verify(detailRepository).setFavouriteMovies(movie.value as MovieEntity)
        verifyNoMoreInteractions(observerMovie)
    }

    @Test
    fun setFavouriteSeries(){
        val dummySeries = DummyContentTest.getSeriesDummyId<DetailContentViewModelTest>()
        val dummyId = dummySeries.id
        val series = MutableLiveData<SeriesEntity>()
        series.value = dummySeries
        Mockito.`when`(detailRepository.getSeriesId(dummyId)).thenReturn(series)
        model.setFavouriteSeries(dummySeries)
        verify(detailRepository).setFavouriteSeries(series.value as SeriesEntity)
        verifyNoMoreInteractions(observerSeries)
    }
}