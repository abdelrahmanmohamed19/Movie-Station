package com.moviestation.moviestation.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.data.model.Movies
import com.moviestation.moviestation.data.model.People
import com.moviestation.moviestation.data.repositories.HomeRepositoreyImpl
import com.moviestation.moviestation.data.model.Tv
import com.moviestation.moviestation.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase : HomeUseCase) : ViewModel() {

    private val _movieList = MutableStateFlow(emptyList<Movies>())
    val movieList: StateFlow<List<Movies>> = _movieList

    private val _tvList = MutableStateFlow(emptyList<Tv>())
    val tvList: StateFlow<List<Tv>> = _tvList


    private val _peopleList = MutableStateFlow(emptyList<People>())
    val peopleList: StateFlow<List<People>> = _peopleList

    init {
        getTrendingMovies()
        getTrendingTv()
        getTrendingPeople()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            useCase.getTrendingMovies().collect{
                _movieList.value = it
            }

        }

    }


    private fun getTrendingTv() {
        viewModelScope.launch {
         useCase.getTrendingTv().collect{
             _tvList.value = it
         }
        }
    }

    private fun getTrendingPeople() {
        viewModelScope.launch {
          useCase.getTrendingPeople().collect{
               _peopleList.value = it
               Log.i("Abdo11",peopleList.value.toString())
           }

        }
    }
}