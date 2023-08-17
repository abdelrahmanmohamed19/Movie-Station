package com.moviestation.moviestation.domain.usecase.home

import com.moviestation.moviestation.data.remote.dto.People
import com.moviestation.moviestation.domain.repository.HomeRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTrendingPeopleUseCase @Inject constructor(private val repo : HomeRepository) {

    suspend fun getTrendingPeople() : StateFlow<List<People>> {
        return repo.getTrendingPeople()
    }
}