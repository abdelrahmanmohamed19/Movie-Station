package com.moviestation.moviestation.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.comman.Resources
import com.moviestation.moviestation.data.remote.dto.Movie
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.domain.usecase.movies.GetMovieCategoriesUseCase
import com.moviestation.moviestation.domain.usecase.movies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor (
    private val getMovieCategoriesUseCase : GetMovieCategoriesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesViewState())
    val state = _state.asStateFlow()

    fun getMoviesCategoriesList() {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieCategoriesUseCase().onEach {
                when(it) {
                    is Resources.Success -> {
                        _state.value = _state.value.copy(isLoading = false, moviesCategoriesList = it.data !!)
                    }
                    is Resources.Error -> _state.value = _state.value.copy(isLoading = false)
                    is Resources.Loading -> _state.value = _state.value.copy(isLoading = true)

                }
            }.launchIn(viewModelScope)
        }
    }
    fun getMovies(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value.moviesList = emptyList()
            getMoviesUseCase(id).onEach {
                when(it) {
                    is Resources.Success -> {
                        _state.value = _state.value.copy(isLoading = false)
                        _state.value = _state.value.copy(moviesList = it.data!!.map { movie ->
                            Trending(
                                name = movie.name,
                                image = movie.poster,
                                overView = movie.overView,
                                voteAverage = movie.voteAverage)
                        } )
                    }
                    is Resources.Error -> _state.value = _state.value.copy(isLoading = false)
                    is Resources.Loading -> _state.value = _state.value.copy(isLoading = true)
                }
            }.launchIn(viewModelScope)
        }
    }
}