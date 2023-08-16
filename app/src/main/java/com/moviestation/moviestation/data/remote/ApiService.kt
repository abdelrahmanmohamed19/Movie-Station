package com.moviestation.moviestation.data.remote

import com.moviestation.moviestation.data.remote.dto.CategoriesResponse
import com.moviestation.moviestation.data.remote.dto.TrendingMoviesResponse
import com.moviestation.moviestation.data.remote.dto.TrendingPeopleResponse
import com.moviestation.moviestation.data.remote.dto.TrendingTvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(@Query("api_key") ApiKey: String): Response<TrendingMoviesResponse>

    @GET("trending/tv/week")
    suspend fun getTrendingTv(@Query("api_key") ApiKey: String): Response<TrendingTvResponse>

    @GET("trending/person/week")
    suspend fun getTrendingPeople(@Query("api_key") ApiKey: String): Response<TrendingPeopleResponse>

    @GET("search/collection")
    suspend fun getSearchedItem(@Query("api_key") ApiKey: String, @Query("query") SearchedItem : String) : Response<TrendingTvResponse>

    @GET("genre/movie/list")
    suspend fun getMoviesCategorie(@Query("api_key") ApiKey: String): Response<CategoriesResponse>

    @GET("genre/tv/list")
    suspend fun getTvCategorie(@Query("api_key") ApiKey: String): Response<CategoriesResponse>

    @GET("discover/movie")
    suspend fun getMovieCategorieList(@Query("api_key") ApiKey: String,@Query ("with_genres") categorieNumber:Int): Response<TrendingMoviesResponse>

    @GET("discover/tv")
    suspend fun getTvCategorieList(@Query("api_key") ApiKey: String,@Query ("with_genres") categorieNumber:Int): Response<TrendingTvResponse>

}