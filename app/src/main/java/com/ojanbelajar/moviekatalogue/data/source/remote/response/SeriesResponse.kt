package com.ojanbelajar.moviekatalogue.data.source.remote.response

import android.os.Parcelable
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesResponse (
    var results: List<SeriesEntity> = emptyList(),
    var success: Boolean = true
): Parcelable, BaseResponse()