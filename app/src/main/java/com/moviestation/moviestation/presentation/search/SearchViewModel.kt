package com.moviestation.moviestation.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor (private val searchUseCase: SearchUseCase): ViewModel() {

    private val _searchedItem = MutableStateFlow(emptyList<Tv>())
    val searchedItem : StateFlow<List<Tv>> = _searchedItem


    fun getSearchedItem(item : String) {
        viewModelScope.launch {
            searchUseCase.getSearchedItem(item).collect{
                _searchedItem.value = it
            }
        }
    }
}