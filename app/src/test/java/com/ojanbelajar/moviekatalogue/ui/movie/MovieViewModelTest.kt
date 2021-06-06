package com.ojanbelajar.moviekatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.mock
import com.ojanbelajar.moviekatalogue.CoroutinesTestRule
import com.ojanbelajar.moviekatalogue.data.FakeContentRepository
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var model: MovieViewModel

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var contentRepository: FakeContentRepository

    @Before
    fun setUp() {
        model = MovieViewModel(contentRepository)
    }

    @Test
    fun getMovies() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Resource<PagedList<MovieEntity>>>>()
            val dummyMovie = Resource.success(moviePagedList)
            Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
            val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
            movie.value = dummyMovie
            Mockito.`when`(contentRepository.getAllMovies()).thenReturn(movie)

            val result = model.getMovies().value?.data
            Mockito.verify(contentRepository).getAllMovies()
            assertNotNull(result)
            assertEquals(5,result?.size)
            model.getMovies().observeForever(observer)
            Mockito.verify(observer).onChanged(dummyMovie)
        }
    }


    @Test
    fun getMoviesByRate() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Resource<PagedList<MovieEntity>>>>()
            val dummyMovie = Resource.success(moviePagedList)
            Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
            val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
            movie.value = dummyMovie
            Mockito.`when`(contentRepository.getAllMoviesByRate()).thenReturn(movie)

            val result = model.getMoviesByRate().value?.data
            Mockito.verify(contentRepository).getAllMoviesByRate()
            assertNotNull(result)
            assertEquals(5,result?.size)
            model.getMoviesByRate().observeForever(observer)
            Mockito.verify(observer).onChanged(dummyMovie)
        }
    }

    @Test
    fun getMoviesByPopularity() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Resource<PagedList<MovieEntity>>>>()
            val dummyMovie = Resource.success(moviePagedList)
            Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
            val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
            movie.value = dummyMovie
            Mockito.`when`(contentRepository.getAllMoviesByPopularity()).thenReturn(movie)

            val result = model.getMoviesByPopularity().value?.data
            Mockito.verify(contentRepository).getAllMoviesByPopularity()
            assertNotNull(result)
            assertEquals(5,result?.size)
            model.getMoviesByPopularity().observeForever(observer)
            Mockito.verify(observer).onChanged(dummyMovie)
        }
    }


}