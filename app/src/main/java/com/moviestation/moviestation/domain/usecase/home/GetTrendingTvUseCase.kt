package com.moviestation.moviestation.domain.usecase.home

import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.domain.repository.HomeRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetTrendingTvUseCase @Inject constructor(private val repo : HomeRepository) {

    suspend fun getTrendingTv () : StateFlow<List<Tv>> {
        return repo.getTrendingTv()
    }
}