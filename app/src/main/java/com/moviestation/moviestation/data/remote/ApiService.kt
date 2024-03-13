package com.moviestation.moviestation.data.remote

import com.moviestation.moviestation.data.remote.dto.CategoriesDto
import com.moviestation.moviestation.data.remote.dto.SearchedItemDto
import com.moviestation.moviestation.data.remote.dto.MoviesDto
import com.moviestation.moviestation.data.remote.dto.TrendingPeopleDto
import com.moviestation.moviestation.data.remote.dto.TvDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(@Query("api_key") apiKey: String): MoviesDto

    @GET("trending/tv/week")
    suspend fun getTrendingTvShows(@Query("api_key") apiKey: String): TvDto

    @GET("trending/person/week")
    suspend fun getTrendingPeople(@Query("api_key") apiKey: String): TrendingPeopleDto

    @GET("search/collection")
    suspend fun getSearchedItem(@Query("api_key") apiKey: String, @Query("query") searchedItem : String) : SearchedItemDto

    @GET("genre/movie/list")
    suspend fun getMovieCategories(@Query("api_key") apiKey: String): CategoriesDto

    @GET("genre/tv/list")
    suspend fun getTvCategories(@Query("api_key") apiKey: String): CategoriesDto

    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String, @Query ("with_genres") id: Int): MoviesDto

    @GET("discover/tv")
    suspend fun getTvShows(@Query("api_key") apiKey: String, @Query ("with_genres") id: Int): TvDto

}