package com.moviestation.moviestation.domain.repository

import com.moviestation.moviestation.data.remote.dto.Tv
import kotlinx.coroutines.flow.StateFlow

interface SearchRepository {

    suspend fun getSearchedItem(item : String) : StateFlow<List<Tv>>

}