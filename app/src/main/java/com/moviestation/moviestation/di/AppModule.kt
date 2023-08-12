package com.moviestation.moviestation.di

import com.example.moviestation.domain.repositories.HomeRepositorey
import com.example.moviestation.domain.repositories.MoviesRepositorey
import com.example.moviestation.domain.repositories.SearchRepositorey
import com.example.moviestation.domain.repositories.TvRepositorey
import com.moviestation.moviestation.data.api.ApiService
import com.moviestation.moviestation.data.repositories.SearchRepositoreyImpl
import com.moviestation.moviestation.data.repositories.HomeRepositoreyImpl
import com.moviestation.moviestation.data.repositories.TvRepositoreyImpl
import com.moviestation.moviestation.data.model.Constants
import com.moviestation.moviestation.data.repositories.MoviesRepositoreyImpl
import com.moviestation.moviestation.domain.usecase.HomeUseCase
import com.moviestation.moviestation.domain.usecase.MovieUseCase
import com.moviestation.moviestation.domain.usecase.SearchUseCase
import com.moviestation.moviestation.domain.usecase.TvUseCase
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
    fun provideHomeRepositorey() : HomeRepositorey {
        return HomeRepositoreyImpl(provideApiService(), provideApiKey())
    }

    @Provides
    @Singleton
    fun provideSearchRepoitoery() : SearchRepositorey {
        return SearchRepositoreyImpl(provideApiService(), provideApiKey())
    }

    @Provides
    @Singleton
    fun provideMoviesRepositorey() : MoviesRepositorey {
        return MoviesRepositoreyImpl(provideApiService(), provideApiKey())
    }

    @Provides
    @Singleton
    fun provideTvRepositorey() : TvRepositorey {
        return TvRepositoreyImpl(provideApiService(), provideApiKey())
    }

    @Provides
    @Singleton
    fun provideHomeUseCase(repo : HomeRepositorey) : HomeUseCase {
        return HomeUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideMovieUseCase(repo : MoviesRepositorey) : MovieUseCase {
        return MovieUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideTvUseCase(repo : TvRepositorey) : TvUseCase {
        return TvUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(repo : SearchRepositorey) : SearchUseCase {
        return SearchUseCase(repo)
    }


}