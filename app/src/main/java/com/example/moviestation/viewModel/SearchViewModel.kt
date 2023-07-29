package com.example.moviestation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestation.data.repositories.SearchRepositorey
import com.example.moviestation.model.TrendingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor (val repo : SearchRepositorey): ViewModel() {

    private val _searchedItem = MutableStateFlow(emptyList<TrendingMovies>())
    val searchedItem : StateFlow<List<TrendingMovies>> = _searchedItem


    fun getSearchedItem(item : String) {
        viewModelScope.launch {
            repo.getSearchedItem(item).collect{
                _searchedItem.value = it
            }
        }
    }
}