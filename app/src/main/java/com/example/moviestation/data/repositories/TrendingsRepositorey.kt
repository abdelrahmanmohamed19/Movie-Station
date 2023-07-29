package com.example.moviestation.data.repositories

import android.util.Log
import com.example.moviestation.data.api.ApiService
import com.example.moviestation.model.Constants
import com.example.moviestation.model.TrendingMovies
import com.example.moviestation.model.TrendingPeople
import com.example.moviestation.model.TrendingTv
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TrendingsRepositorey @Inject constructor(private val api : ApiService , private val apiKey : String){

    suspend fun getTrendingMovies () : StateFlow<List<TrendingMovies>> {

        val movieList = MutableStateFlow(emptyList<TrendingMovies>())

        val response = api.getTrendingMovies(apiKey)
        if (response.isSuccessful) {
            val responseBody = response.body()?.TrendingMovies
            movieList.value = responseBody !!
        }
        return movieList
    }

    suspend fun getTrendingTv () : StateFlow<List<TrendingTv>>{

        val tvList = MutableStateFlow(emptyList<TrendingTv>())

        val response = api.getTrendingTv(apiKey)
        if (response.isSuccessful) {
            val responseBody = response.body()?.TrendingTv
            tvList.value = responseBody !!
        }
        return tvList
    }

    suspend fun getTrendingPeople() : StateFlow<List<TrendingPeople>>{

        val peopleList = MutableStateFlow(emptyList<TrendingPeople>())

        val response = api.getTrendingPeople(apiKey)
        if (response.isSuccessful) {
            val responseBody = response.body()?.TrendingPeople
            peopleList.value = responseBody !!
        }
        return peopleList
    }
}