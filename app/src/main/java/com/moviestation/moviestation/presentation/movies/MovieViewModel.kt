package com.moviestation.moviestation.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.data.remote.dto.Categories
import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.domain.usecase.movies.GetMovieCategoriesUseCase
import com.moviestation.moviestation.domain.usecase.movies.GetMoviesCategoriesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (
    private val moviesCategoriesUseCase : GetMovieCategoriesUseCase,
    private val moviesCategoriesListUseCase: GetMoviesCategoriesListUseCase,
) : ViewModel() {

    private val _categoriesList = MutableStateFlow(emptyList<Categories>())
    val categoriesList : StateFlow<List<Categories>> = _categoriesList

    private val _movieCategoriesList = MutableStateFlow(emptyList<Movies>())
    val movieCategoriesList : StateFlow<List<Movies>> = _movieCategoriesList

    init {
        getCategoriesList()
    }

    private fun getCategoriesList(){
        viewModelScope.launch(Dispatchers.IO) {
            moviesCategoriesUseCase.getMovieCategories().collect{
                _categoriesList.value = it
            }
        }
    }

    fun getMoviesCategoriesList(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            moviesCategoriesListUseCase.getMoviesCategoriesList(id).collect{
                _movieCategoriesList.value = it
            }
        }
    }
}