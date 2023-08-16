package com.moviestation.moviestation.data.repository

import com.moviestation.moviestation.data.remote.ApiService
import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.data.remote.dto.People
import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(private val api : ApiService, private val apiKey : String) :
    HomeRepository {

    override suspend fun getTrendingMovies () : StateFlow<List<Movies>> {

        val movieList = MutableStateFlow(emptyList<Movies>())

        val response = api.getTrendingMovies(apiKey)
        if (response.isSuccessful) {
            val responseBody = response.body()?.trendingMovies
            movieList.value = responseBody !!
        }
        return movieList
    }

    override suspend fun getTrendingTv () : StateFlow<List<Tv>>{

        val tvList = MutableStateFlow(emptyList<Tv>())

        val response = api.getTrendingTv(apiKey)
        if (response.isSuccessful) {
            val responseBody = response.body()?.trendingTv
            tvList.value = responseBody !!
        }
        return tvList
    }

    override suspend fun getTrendingPeople() : StateFlow<List<People>>{

        val peopleList = MutableStateFlow(emptyList<People>())

        val response = api.getTrendingPeople(apiKey)
        if (response.isSuccessful) {
            val responseBody = response.body()?.trendingPeople
            peopleList.value = responseBody !!
        }
        return peopleList
    }
}