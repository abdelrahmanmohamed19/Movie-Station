package com.moviestation.moviestation.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.comman.ApiResponse
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.domain.usecase.home.GetTrendingPeopleUseCase
import com.moviestation.moviestation.domain.usecase.home.GetTrendingMoviesUseCase
import com.moviestation.moviestation.domain.usecase.home.GetTrendingTvShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val  trendingMoviesUseCase : GetTrendingMoviesUseCase,
    private val trendingTvShowsUseCase : GetTrendingTvShowsUseCase,
    private val trendingPeopleUseCase : GetTrendingPeopleUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(HomeUIState())
    val state = _state.asStateFlow()

    init {
        getTrendingMovies()
        getTrendingTvShows()
        getTrendingPeople()
    }

    private fun getTrendingMovies() {
        trendingMoviesUseCase().onEach {
            when (it) {
                is ApiResponse.Success -> {
                    _state.value = _state.value.copy(isLoadingMovies = false)
                    it.data?.forEach { movie ->
                        _state.value = _state.value.copy(trendingMoviesList = _state.value.trendingMoviesList +
                                Trending(
                                    name = movie.name,
                                    image = movie.poster,
                                    voteAverage = movie.voteAverage,
                                    overView = movie.overView
                                ))
                    }
                }
                is ApiResponse.Error -> _state.value = _state.value.copy(isLoadingMovies = false)
                is ApiResponse.Loading -> _state.value = _state.value.copy(isLoadingMovies = true)
            }
        }.launchIn(viewModelScope)
    }
    private fun getTrendingTvShows () {
        trendingTvShowsUseCase().onEach {
            when (it) {
                is ApiResponse.Success -> {
                    _state.value = _state.value.copy(isLoadingTvShows = false )
                    it.data?.forEach { tvShow ->
                        _state.value = _state.value.copy(trendingTvShowsList = _state.value.trendingTvShowsList +
                                Trending(
                                    name = tvShow.name,
                                    image = tvShow.poster,
                                    voteAverage = tvShow.voteAverage,
                                    overView = tvShow.overView
                                ))
                    }
                }
                is ApiResponse.Error -> _state.value = _state.value.copy(isLoadingTvShows = false )
                is ApiResponse.Loading -> _state.value = _state.value.copy(isLoadingTvShows = true )
            }
        }.launchIn(viewModelScope)
    }

    private fun getTrendingPeople() {
        trendingPeopleUseCase().onEach {
            when (it) {
                is ApiResponse.Success -> {
                    _state.value = _state.value.copy(isLoadingPeople = false)
                    it.data?.forEach { person ->
                        _state.value =  _state.value.copy(trendingPeopleList = _state.value.trendingPeopleList +
                                Trending(
                                    name = person.name,
                                    image = person.poster,
                                ))
                    }
                }
                is ApiResponse.Error -> _state.value = _state.value.copy(isLoadingPeople = false)
                is ApiResponse.Loading -> _state.value = _state.value.copy(isLoadingPeople = true)
            }
        }.launchIn(viewModelScope)
    }
}