package com.example.moviestation.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestation.data.repositories.TrendingsRepositorey
import com.example.moviestation.model.Constants
import com.example.moviestation.model.TrendingMovies
import com.example.moviestation.model.TrendingPeople
import com.example.moviestation.model.TrendingTv
//import com.example.moviestation.model.TrendingTv
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val repo: TrendingsRepositorey) : ViewModel() {

    private val _movieList = MutableStateFlow(emptyList<TrendingMovies>())
    val movieList: StateFlow<List<TrendingMovies>> = _movieList

    private val _tvList = MutableStateFlow(emptyList<TrendingTv>())
    val tvList: StateFlow<List<TrendingTv>> = _tvList


    private val _peopleList = MutableStateFlow(emptyList<TrendingPeople>())
    val peopleList: StateFlow<List<TrendingPeople>> = _peopleList

    init {
        getTrendingMovies()
        getTrendingTv()
        getTrendingPeople()
    }

    fun getTrendingMovies() {
        viewModelScope.launch {
            repo.getTrendingMovies().collect{
                _movieList.value = it
            }

        }

    }


    fun getTrendingTv() {
        viewModelScope.launch {
         repo.getTrendingTv().collect{
             _tvList.value = it
         }
        }
    }

    fun getTrendingPeople() {
        viewModelScope.launch {
           repo.getTrendingPeople().collect{
               _peopleList.value = it
           }

        }
    }
}