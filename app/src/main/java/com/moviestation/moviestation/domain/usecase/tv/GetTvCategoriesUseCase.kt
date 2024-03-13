package com.moviestation.moviestation.domain.usecase.tv

import com.moviestation.moviestation.comman.Resources
import com.moviestation.moviestation.domain.repository.TvRepository
import com.moviestation.moviestation.data.remote.dto.Categories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTvCategoriesUseCase @Inject constructor(private val tvRepository : TvRepository){

    operator fun invoke () : Flow<Resources<List<Categories>>> = flow  {
        try {
            emit(Resources.Loading())
            val data = tvRepository.getTvCategories().categoriesList
            emit(Resources.Success(data))
        } catch (e: HttpException) {
            emit(Resources.Error("an error occurred, please try again later"))
        } catch (e: IOException) {
            emit(Resources.Error("no internet connection"))
        }
    }
}