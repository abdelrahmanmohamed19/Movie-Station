package com.moviestation.moviestation.domain.usecase.home

import com.moviestation.moviestation.comman.Resources
import com.moviestation.moviestation.data.remote.dto.People
import com.moviestation.moviestation.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTrendingPeopleUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    operator fun invoke () : Flow<Resources<List<People>>> = flow {
        try {
            emit(Resources.Loading())
            val data = homeRepository.getTrendingPeople().trendingPeople
            emit(Resources.Success(data))
        } catch (e: HttpException) {
            emit(Resources.Error("an error occurred, please try again later"))
        } catch (e: IOException) {
            emit(Resources.Error("no internet connection"))
        }
    }
}