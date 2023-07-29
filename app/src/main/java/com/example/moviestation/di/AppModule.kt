package com.example.moviestation.di

import com.example.moviestation.data.api.ApiService
import com.example.moviestation.data.repositories.SearchRepositorey
import com.example.moviestation.data.repositories.TrendingsRepositorey
import com.example.moviestation.model.Constants
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
    fun provideApiKey(): String {
        return Constants.ApiKey
    }

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
    fun provideRepositorey() : TrendingsRepositorey {
        return TrendingsRepositorey(provideApiService(), provideApiKey())
    }

    @Provides
    @Singleton
    fun provideSearchRepoitoery() : SearchRepositorey {
        return SearchRepositorey(provideApiService(), provideApiKey())
    }


}