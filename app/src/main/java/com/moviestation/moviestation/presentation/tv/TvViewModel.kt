package com.moviestation.moviestation.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviestation.moviestation.comman.ApiResponse
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.domain.usecase.tv.GetTvShowsUseCase
import com.moviestation.moviestation.domain.usecase.tv.GetTvCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor (
    private val getTvCategoriesUseCase: GetTvCategoriesUseCase,
    private val getTvShowsUseCase: GetTvShowsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TvUIState())
    val state = _state.asStateFlow()


    fun getTvShowCategories() {
        getTvCategoriesUseCase().onEach {
            when (it) {
                is ApiResponse.Success -> {
                    _state.value = _state.value.copy(isLoading = false , tvShowCategoriesList = it.data !!)
                }
                is ApiResponse.Error -> _state.value = _state.value.copy(isLoading = false)
                is ApiResponse.Loading -> _state.value = _state.value.copy(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

    fun getTvShows(id : Int) {
        _state.value.tvShowsList = emptyList()
        getTvShowsUseCase(id).onEach {
            when (it) {
                is ApiResponse.Success -> {
                    _state.value = _state.value.copy(isLoading = false)
                    _state.value = _state.value.copy(tvShowsList = it.data!!.map {tvShow ->
                        Trending (
                            name = tvShow.name,
                            image = tvShow.poster,
                            overView = tvShow.overView,
                            voteAverage = tvShow.voteAverage
                        )
                    })
                }

                is ApiResponse.Error -> _state.value = _state.value.copy(isLoading = false)
                is ApiResponse.Loading -> _state.value = _state.value.copy(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}