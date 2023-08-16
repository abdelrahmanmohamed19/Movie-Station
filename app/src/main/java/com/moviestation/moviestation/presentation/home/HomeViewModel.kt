package com.moviestation.moviestation.presentation.home


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.data.remote.dto.People
import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.domain.usecase.home.GetTrendingPeopleUseCase
import com.moviestation.moviestation.domain.usecase.home.GetTrendingMoviesUseCase
import com.moviestation.moviestation.domain.usecase.home.GetTrendingTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val  trendingMoviesUseCase : GetTrendingMoviesUseCase,
    private val trendingTvUseCase : GetTrendingTvUseCase,
    private val trendingPeopleUseCase : GetTrendingPeopleUseCase
) : ViewModel() {

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
        viewModelScope.launch(Dispatchers.IO) {
            trendingMoviesUseCase.getTrendingMovies().collect{
                _movieList.value = it
            }

        }

    }


    private fun getTrendingTv() {
        viewModelScope.launch(Dispatchers.IO) {
         trendingTvUseCase.getTrendingTv().collect{
             _tvList.value = it
         }
        }
    }

    private fun getTrendingPeople() {
        viewModelScope.launch(Dispatchers.IO) {
          trendingPeopleUseCase.getTrendingPeople().collect{
               _peopleList.value = it
           }

        }
    }
}