package com.moviestation.moviestation.di

import com.moviestation.moviestation.domain.repository.HomeRepository
import com.moviestation.moviestation.domain.repository.MoviesRepository
import com.moviestation.moviestation.domain.repository.SearchRepository
import com.moviestation.moviestation.domain.repository.TvRepository
import com.moviestation.moviestation.data.remote.ApiService
import com.moviestation.moviestation.data.repository.SearchRepositoryImpl
import com.moviestation.moviestation.data.repository.TvRepositoryImpl
import com.moviestation.moviestation.comman.Constants
import com.moviestation.moviestation.data.repository.HomeRepositoryImpl
import com.moviestation.moviestation.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiKey(): String = Constants.ApiKey

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository() : HomeRepository {
        return HomeRepositoryImpl(provideApiService(), provideApiKey())
    }

    @Provides
    @Singleton
    fun provideSearchRepository() : SearchRepository {
        return SearchRepositoryImpl(provideApiService(), provideApiKey())
    }

    @Provides
    @Singleton
    fun provideMoviesRepository() : MoviesRepository {
        return MoviesRepositoryImpl(provideApiService(), provideApiKey())
    }

    @Provides
    @Singleton
    fun provideTvRepository() : TvRepository {
        return TvRepositoryImpl(provideApiService(), provideApiKey())
    }

}