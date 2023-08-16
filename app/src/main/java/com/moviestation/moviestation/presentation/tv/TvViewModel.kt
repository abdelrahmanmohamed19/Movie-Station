package com.moviestation.moviestation.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.data.remote.dto.Categories
import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.domain.usecase.tv.GetTvCategoriesListUseCase
import com.moviestation.moviestation.domain.usecase.tv.GetTvCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor (
    private val getTvCategoriesUseCase: GetTvCategoriesUseCase,
    private val getTvCategoriesListUseCase: GetTvCategoriesListUseCase
) : ViewModel() {

    private val _categoriesList = MutableStateFlow(emptyList<Categories>())
    val categoriesList : StateFlow<List<Categories>> = _categoriesList

    private val _tvCategoriesList = MutableStateFlow(emptyList<Tv>())
    val tvCategoriesList : StateFlow<List<Tv>> = _tvCategoriesList

    init {
        getCategoriesList()
    }

    private fun getCategoriesList(){
        viewModelScope.launch(Dispatchers.IO) {
            getTvCategoriesUseCase.getTvCategories().collect{
                _categoriesList.value = it
            }
        }
    }

    fun getTvCategoriesList(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            getTvCategoriesListUseCase.getTvCategoriesList(id).collect{
                _tvCategoriesList.value = it
            }
        }
    }

}