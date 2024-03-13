package com.moviestation.moviestation.presentation.home

import com.moviestation.moviestation.domain.model.Trending

data class HomeViewState(
  var trendingMoviesList: List<Trending> = emptyList(),
  var trendingTvShowsList: List<Trending> = emptyList(),
  var trendingPeopleList: List<Trending> = emptyList(),
  var isLoadingMovies: Boolean = false,
  var isLoadingTvShows: Boolean = false,
  var isLoadingPeople: Boolean = false
)
