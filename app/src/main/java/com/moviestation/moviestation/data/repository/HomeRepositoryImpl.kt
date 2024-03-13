package com.moviestation.moviestation.data.repository

import com.moviestation.moviestation.data.remote.ApiService
import com.moviestation.moviestation.data.remote.dto.MoviesDto
import com.moviestation.moviestation.data.remote.dto.TrendingPeopleDto
import com.moviestation.moviestation.data.remote.dto.TvDto
import com.moviestation.moviestation.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: ApiService, private val apiKey: String) : HomeRepository {

    override suspend fun getTrendingMovies(): MoviesDto {
        return apiService.getTrendingMovies(apiKey)
    }

    override suspend fun getTrendingTvShows(): TvDto {
        return apiService.getTrendingTvShows(apiKey)
    }

    override suspend fun getTrendingPeople(): TrendingPeopleDto {
        return apiService.getTrendingPeople(apiKey)
    }
}