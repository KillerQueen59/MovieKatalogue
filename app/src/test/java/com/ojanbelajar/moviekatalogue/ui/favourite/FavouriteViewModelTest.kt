package com.ojanbelajar.moviekatalogue.ui.favourite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ojanbelajar.moviekatalogue.data.FakeContentRepository
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavouriteViewModelTest {

    private lateinit var model: FavouriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: FakeContentRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerSeries: Observer<PagedList<SeriesEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var seriesPagedList: PagedList<SeriesEntity>

    @Before
    fun setup(){
        model = FavouriteViewModel(contentRepository)
    }

    @Test
    fun getFavouriteMovie() {
        val dummyMovie = moviePagedList
        Mockito.`when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie
        Mockito.`when`(contentRepository.getFavouriteMovies()).thenReturn(movie)
        val result = model.getFavouriteMovie().value
        Mockito.verify(contentRepository).getFavouriteMovies()
        Assert.assertNotNull(result)
        Assert.assertEquals(5, result?.size)
        model.getFavouriteMovie().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getFavouriteSeries() {
        val dummySeries = seriesPagedList
        Mockito.`when`(dummySeries.size).thenReturn(5)
        val series = MutableLiveData<PagedList<SeriesEntity>>()
        series.value = dummySeries
        Mockito.`when`(contentRepository.getFavouriteSeries()).thenReturn(series)
        val result = model.getFavouriteSeries().value
        Mockito.verify(contentRepository).getFavouriteSeries()
        Assert.assertNotNull(result)
        Assert.assertEquals(5, result?.size)
        model.getFavouriteSeries().observeForever(observerSeries)
        Mockito.verify(observerSeries).onChanged(dummySeries)
    }
}