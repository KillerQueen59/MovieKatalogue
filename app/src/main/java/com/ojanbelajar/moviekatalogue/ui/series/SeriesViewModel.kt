package com.ojanbelajar.moviekatalogue.ui.series

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ojanbelajar.moviekatalogue.data.source.Repository
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import com.ojanbelajar.moviekatalogue.utils.Resource

class SeriesViewModel @ViewModelInject constructor(
        private val contentRepository: Repository
): ViewModel() {

    fun getSeries(): LiveData<Resource<PagedList<SeriesEntity>>> = contentRepository.getAllSeries()

    fun getSeriesByRate(): LiveData<Resource<PagedList<SeriesEntity>>> = contentRepository.getAllSeriesByRate()

    fun getSeriesByPopularity(): LiveData<Resource<PagedList<SeriesEntity>>> = contentRepository.getAllSeriesByPopularity()
}