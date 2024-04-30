package com.moviestation.moviestation.domain.usecase.search

import com.moviestation.moviestation.comman.ApiResponse
import com.moviestation.moviestation.data.remote.dto.Item
import com.moviestation.moviestation.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(searchedItem: String): Flow<ApiResponse<List<Item>>> = flow {
        try {
            emit(ApiResponse.Loading())
            val data = searchRepository.getSearchedItem(searchedItem).results
            emit(ApiResponse.Success(data))
        } catch (e: HttpException) {
            emit(ApiResponse.Error("an error occurred, please try again later"))
        } catch (e: IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }
}
