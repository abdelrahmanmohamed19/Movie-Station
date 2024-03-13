package com.moviestation.moviestation.domain.usecase.movies

import com.moviestation.moviestation.comman.Resources
import com.moviestation.moviestation.data.remote.dto.Movie
import com.moviestation.moviestation.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase  @Inject constructor(private val moviesRepository : MoviesRepository) {

  operator fun invoke (id: Int): Flow<Resources<List<Movie>>> = flow {
      try {
          emit(Resources.Loading())
          val data = moviesRepository.getMovies(id).trendingMovies
          emit(Resources.Success(data))
      } catch (e: HttpException) {
          emit(Resources.Error("an error has been occurred, try again"))
      } catch (e: IOException) {
          emit(Resources.Error("no internet connection"))
      }
  }
}