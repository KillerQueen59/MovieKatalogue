package com.ojanbelajar.moviekatalogue.di

import android.app.Application
import com.ojanbelajar.moviekatalogue.data.source.remote.RemoteRemoteSource
import com.ojanbelajar.moviekatalogue.api.ApiService
import com.ojanbelajar.moviekatalogue.data.source.remote.ContentRemoteSource
import com.ojanbelajar.moviekatalogue.data.source.ContentRepository
import com.ojanbelajar.moviekatalogue.data.source.Repository
import com.ojanbelajar.moviekatalogue.data.source.local.ContentLocalSource
import com.ojanbelajar.moviekatalogue.data.source.local.LocalDataSource
import com.ojanbelajar.moviekatalogue.data.source.local.room.ContentDao
import com.ojanbelajar.moviekatalogue.data.source.local.room.ContentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideBase() = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient,BASEURL: String): Retrofit = Retrofit.Builder().apply {
        baseUrl(BASEURL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideContentRemoteSource(remoteSource: RemoteRemoteSource): ContentRemoteSource = remoteSource

    @Provides
    @Singleton
    fun provideContentLocalSource(localSource: LocalDataSource): ContentLocalSource =  localSource

    @Provides
    @Singleton
    fun provideContentRepository(
        contentRemoteSource: ContentRemoteSource,
        contentLocalSource: ContentLocalSource,
    ): Repository = ContentRepository(contentRemoteSource,contentLocalSource)

    @Singleton
    @Provides
    fun provideContentDatabase(
        application: Application
    ): ContentDatabase = ContentDatabase.getInstance(application)

    @Singleton
    @Provides
    fun contentDao(db: ContentDatabase): ContentDao = db.contentDao()


}