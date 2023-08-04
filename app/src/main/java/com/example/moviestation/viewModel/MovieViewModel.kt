package com.example.moviestation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviestation.R
import com.example.moviestation.data.repositories.MovieRepositorey
import com.example.moviestation.model.Categorie
import com.example.moviestation.model.TrendingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (private val repo : MovieRepositorey) : ViewModel() {

    private val _CategorieList = MutableStateFlow(emptyList<Categorie>())
    val CategorieList : StateFlow<List<Categorie>> = _CategorieList

    private val _MovieCategorieList = MutableStateFlow(emptyList<TrendingMovies>())
    val MovieCategorieList : StateFlow<List<TrendingMovies>> = _MovieCategorieList

    init {
        getCategorieList()
    }

    fun getCategorieList(){
        viewModelScope.launch {
            repo.getMovieCategories().collect{
                _CategorieList.value = it
            }
        }
    }

    fun getMovieCategorieList(id : Int){
        viewModelScope.launch {
            repo.getMovieCategorieList(id).collect{
                _MovieCategorieList.value = it
            }
        }
    }
}