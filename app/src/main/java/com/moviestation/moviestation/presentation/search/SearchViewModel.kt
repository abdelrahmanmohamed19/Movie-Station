package com.moviestation.moviestation.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.comman.Resources
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor (private val searchUseCase: SearchUseCase): ViewModel() {

    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _searchedItemList = MutableStateFlow(emptyList<Trending>())
    val searchedItemList = _searchedItemList.asStateFlow()
    fun getSearchedItem(item: String) {
        searchUseCase(item).onEach {
            when (it) {
                is Resources.Success -> {
                    _isLoading.value = false
                    _searchedItemList.value = it.data?.map { item ->
                        Trending(
                            name = item.name,
                            image = item.poster,
                            overView = item.overView,
                            voteAverage = 1.1
                        )
                    } ?: emptyList()
                }
                is Resources.Error -> _isLoading.value = false
                is Resources.Loading -> _isLoading.value = true
            }
        }.launchIn(viewModelScope)
    }

}