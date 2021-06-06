package com.ojanbelajar.moviekatalogue.data.source.remote.response

import android.os.Parcelable
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    var results: List<MovieEntity> = emptyList(),
    var success: Boolean = true
): Parcelable , BaseResponse()