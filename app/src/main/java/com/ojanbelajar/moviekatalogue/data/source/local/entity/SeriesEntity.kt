package com.ojanbelajar.moviekatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ojanbelajar.moviekatalogue.data.source.remote.response.BaseResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "series")
data class SeriesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "seriesId")
    var id: String = "",

    @ColumnInfo(name = "original_name")
    var original_name: String = "",

    @ColumnInfo(name = "overview")
    var overview: String = "",

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "first_air_date")
    var first_air_date: String = "",


    @ColumnInfo(name = "vote_average")
    var vote_average: Double = 0.0,

    @ColumnInfo(name = "poster_path")
    var poster_path: String = "",

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String = "",

    @ColumnInfo(name = "isFavorit")
    var isFavorite: Boolean = false
): Parcelable, BaseResponse()
