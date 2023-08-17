package com.moviestation.moviestation.domain.repository

import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.data.remote.dto.People
import com.moviestation.moviestation.data.remote.dto.Tv
import kotlinx.coroutines.flow.StateFlow

interface HomeRepository{

    suspend fun getTrendingMovies () : StateFlow<List<Movies>>

    suspend fun getTrendingTv () : StateFlow<List<Tv>>

    suspend fun getTrendingPeople() : StateFlow<List<People>>
}