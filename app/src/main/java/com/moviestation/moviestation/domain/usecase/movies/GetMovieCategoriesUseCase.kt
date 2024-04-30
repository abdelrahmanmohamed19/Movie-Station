package com.moviestation.moviestation.domain.usecase.movies

import com.moviestation.moviestation.comman.ApiResponse
import com.moviestation.moviestation.domain.repository.MoviesRepository
import com.moviestation.moviestation.data.remote.dto.Categories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieCategoriesUseCase @Inject constructor(private val moviesRepository : MoviesRepository)  {

    operator fun invoke (): Flow<ApiResponse<List<Categories>>> = flow {
        try {
            emit(ApiResponse.Loading())
            val data = moviesRepository.getMovieCategories().categoriesList
            emit(ApiResponse.Success(data))
        } catch (e: HttpException) {
            emit(ApiResponse.Error("an error has been occurred, try again"))
        } catch (e: IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }
}