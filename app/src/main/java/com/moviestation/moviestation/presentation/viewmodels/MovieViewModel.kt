package com.moviestation.moviestation.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.data.model.Categories
import com.moviestation.moviestation.data.model.Movies
import com.moviestation.moviestation.data.repositories.MoviesRepositoreyImpl
import com.moviestation.moviestation.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (private val useCase : MovieUseCase) : ViewModel() {

    private val _CategorieList = MutableStateFlow(emptyList<Categories>())
    val CategorieList : StateFlow<List<Categories>> = _CategorieList

    private val _MovieCategorieList = MutableStateFlow(emptyList<Movies>())
    val MovieCategorieList : StateFlow<List<Movies>> = _MovieCategorieList

    init {
        getCategorieList()
    }

    fun getCategorieList(){
        viewModelScope.launch {
            useCase.getMovieCategories().collect{
                _CategorieList.value = it
            }
        }
    }

    fun getMoviesCategorieList(id : Int){
        viewModelScope.launch {
            useCase.getMoviesCategorieList(id).collect{
                _MovieCategorieList.value = it
                Log.i("WelcomeMovie",MovieCategorieList.value.toString())
            }
        }
    }
}