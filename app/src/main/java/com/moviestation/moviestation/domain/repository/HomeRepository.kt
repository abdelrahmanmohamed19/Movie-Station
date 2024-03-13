package com.moviestation.moviestation.domain.repository

import com.moviestation.moviestation.data.remote.dto.MoviesDto
import com.moviestation.moviestation.data.remote.dto.TrendingPeopleDto
import com.moviestation.moviestation.data.remote.dto.TvDto

interface HomeRepository {
    suspend fun getTrendingMovies(): MoviesDto
    suspend fun getTrendingTvShows(): TvDto
    suspend fun getTrendingPeople(): TrendingPeopleDto
}