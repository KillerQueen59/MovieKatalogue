package com.ojanbelajar.moviekatalogue

import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity


object DummyContentTest {

    val MOVIE_DUMMY = "movies_dummy.json"
    val SERIES_DUMMY = "series_dummy.json"
    val MOVIE_DUMMY_ID = "dummy_movie_id.json"
    val SERIES_DUMMY_ID = "dummy_series_id.json"

    inline fun <reified BASE> getMoviesDummy(): List<MovieEntity> {
        return JsonToPojoConverter.convertJson<BASE, List<MovieEntity>>(MOVIE_DUMMY)
    }

    inline fun <reified BASE> getSeriesDummy(): List<SeriesEntity> {
        return JsonToPojoConverter.convertJson<BASE, List<SeriesEntity>>(SERIES_DUMMY)
    }

    inline fun <reified BASE> getMovieDummyId(): MovieEntity {
        return JsonToPojoConverter.convertJson<BASE, MovieEntity>(MOVIE_DUMMY_ID)
    }

    inline fun <reified BASE> getSeriesDummyId(): SeriesEntity {
        return JsonToPojoConverter.convertJson<BASE, SeriesEntity>(SERIES_DUMMY_ID)
    }

}