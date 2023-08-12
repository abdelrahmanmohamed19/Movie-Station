package com.moviestation.moviestation.data.repositories

import com.moviestation.moviestation.data.api.ApiService
import com.moviestation.moviestation.data.model.Movies
import com.moviestation.moviestation.data.model.People
import com.moviestation.moviestation.data.model.Tv
import com.example.moviestation.domain.repositories.HomeRepositorey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class HomeRepositoreyImpl @Inject constructor(private val api : ApiService, private val apiKey : String) : HomeRepositorey {

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