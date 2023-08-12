package com.moviestation.moviestation.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.data.repositories.TvRepositoreyImpl
import com.moviestation.moviestation.data.model.Categories
import com.moviestation.moviestation.data.model.Tv
import com.moviestation.moviestation.domain.usecase.SearchUseCase
import com.moviestation.moviestation.domain.usecase.TvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor (private val useCase: TvUseCase) : ViewModel() {

    private val _CategorieList = MutableStateFlow(emptyList<Categories>())
    val CategorieList : StateFlow<List<Categories>> = _CategorieList

    private val _TvCategorieList = MutableStateFlow(emptyList<Tv>())
    val TvCategorieList : StateFlow<List<Tv>> = _TvCategorieList

    init {
        getCategorieList()
    }

    fun getCategorieList(){
        viewModelScope.launch {
            useCase.getTvCategories().collect{
                _CategorieList.value = it
                Log.i("AbdoRepo",CategorieList.value.toString())
            }
        }
    }

    fun getTvCategorieList(id : Int){
        viewModelScope.launch {
            useCase.getTvCategorieList(id).collect{
                _TvCategorieList.value = it
            }
        }
    }

}