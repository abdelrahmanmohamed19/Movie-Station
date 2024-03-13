package com.moviestation.moviestation.domain.repository

import com.moviestation.moviestation.data.remote.dto.SearchedItemDto

interface SearchRepository {

    suspend fun getSearchedItem(searchedItem : String) : SearchedItemDto

}