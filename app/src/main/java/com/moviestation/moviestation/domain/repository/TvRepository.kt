package com.moviestation.moviestation.domain.repository

import com.moviestation.moviestation.data.remote.dto.CategoriesDto
import com.moviestation.moviestation.data.remote.dto.TvDto

interface TvRepository {

    suspend fun getTvCategories() : CategoriesDto

    suspend fun getTvShows(id : Int) : TvDto
}