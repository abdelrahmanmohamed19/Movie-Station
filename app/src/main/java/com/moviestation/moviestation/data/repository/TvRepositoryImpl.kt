package com.moviestation.moviestation.data.repository

import com.moviestation.moviestation.domain.repository.TvRepository
import com.moviestation.moviestation.data.remote.ApiService
import com.moviestation.moviestation.data.remote.dto.CategoriesDto
import com.moviestation.moviestation.data.remote.dto.TvDto
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor (private val apiService : ApiService, private val apiKey : String ) : TvRepository {
    override suspend fun getTvCategories(): CategoriesDto {
        return apiService.getTvCategories(apiKey)
    }

    override suspend fun getTvShows(id: Int): TvDto {
        return apiService.getTvShows(apiKey, id)
    }

}