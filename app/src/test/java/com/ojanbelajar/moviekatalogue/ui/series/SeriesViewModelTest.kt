package com.ojanbelajar.moviekatalogue.ui.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.mock
import com.ojanbelajar.moviekatalogue.CoroutinesTestRule
import com.ojanbelajar.moviekatalogue.data.FakeContentRepository
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.utils.Resource
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
class SeriesViewModelTest {

    private lateinit var model: SeriesViewModel

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var seriesPagedList: PagedList<SeriesEntity>

    @Mock
    private lateinit var seriesRepository: FakeContentRepository

    @Before
    fun setUp() {
        model = SeriesViewModel(seriesRepository)
    }

    @Test
    fun getOnAiring() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Resource<PagedList<SeriesEntity>>>>()
            val dummySeries = Resource.success(seriesPagedList)
            Mockito.`when`(dummySeries.data?.size).thenReturn(5)
            val series = MutableLiveData<Resource<PagedList<SeriesEntity>>>()
            series.value = dummySeries
            Mockito.`when`(seriesRepository.getAllSeries()).thenReturn(series)

            val result = model.getSeries().value?.data
            Mockito.verify(seriesRepository).getAllSeries()
            assertNotNull(result)
            assertEquals(5,result?.size)
            model.getSeries().observeForever(observer)
            Mockito.verify(observer).onChanged(dummySeries)
        }
    }

    @Test
    fun getSeriesByRate() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Resource<PagedList<SeriesEntity>>>>()
            val dummySeries = Resource.success(seriesPagedList)
            Mockito.`when`(dummySeries.data?.size).thenReturn(5)
            val series = MutableLiveData<Resource<PagedList<SeriesEntity>>>()
            series.value = dummySeries
            Mockito.`when`(seriesRepository.getAllSeriesByRate()).thenReturn(series)

            val result = model.getSeriesByRate().value?.data
            Mockito.verify(seriesRepository).getAllSeriesByRate()
            assertNotNull(result)
            assertEquals(5,result?.size)
            model.getSeriesByRate().observeForever(observer)
            Mockito.verify(observer).onChanged(dummySeries)
        }
    }

    @Test
    fun getSeriesByPopularity() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Resource<PagedList<SeriesEntity>>>>()
            val dummySeries = Resource.success(seriesPagedList)
            Mockito.`when`(dummySeries.data?.size).thenReturn(5)
            val series = MutableLiveData<Resource<PagedList<SeriesEntity>>>()
            series.value = dummySeries
            Mockito.`when`(seriesRepository.getAllSeriesByPopularity()).thenReturn(series)

            val result = model.getSeriesByPopularity().value?.data
            Mockito.verify(seriesRepository).getAllSeriesByPopularity()
            assertNotNull(result)
            assertEquals(5,result?.size)
            model.getSeriesByPopularity().observeForever(observer)
            Mockito.verify(observer).onChanged(dummySeries)
        }
    }


}