package com.moviestation.moviestation.domain.usecase.tv

import com.moviestation.moviestation.comman.ApiResponse
import com.moviestation.moviestation.domain.repository.TvRepository
import com.moviestation.moviestation.data.remote.dto.Categories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTvCategoriesUseCase @Inject constructor(private val tvRepository : TvRepository){

    operator fun invoke () : Flow<ApiResponse<List<Categories>>> = flow  {
        try {
            emit(ApiResponse.Loading())
            val data = tvRepository.getTvCategories().categoriesList
            emit(ApiResponse.Success(data))
        } catch (e: HttpException) {
            emit(ApiResponse.Error("an error occurred, please try again later"))
        } catch (e: IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }
}