package com.example.moviestation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestation.data.repositories.TvRepositorey
import com.example.moviestation.model.Categorie
import com.example.moviestation.model.TrendingMovies
import com.example.moviestation.model.TrendingTv
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor (private val repo : TvRepositorey) : ViewModel() {

    private val _CategorieList = MutableStateFlow(emptyList<Categorie>())
    val CategorieList : StateFlow<List<Categorie>> = _CategorieList

    private val _TvCategorieList = MutableStateFlow(emptyList<TrendingTv>())
    val TvCategorieList : StateFlow<List<TrendingTv>> = _TvCategorieList

    init {
        getCategorieList()
    }

    fun getCategorieList(){
        viewModelScope.launch {
            repo.getTvCategories().collect{
                _CategorieList.value = it
                Log.i("AbdoRepo",CategorieList.value.toString())
            }
        }
    }

    fun getTvCategorieList(id : Int){
        viewModelScope.launch {
            repo.getTvCategorieList(id).collect{
                _TvCategorieList.value = it
            }
        }
    }


}