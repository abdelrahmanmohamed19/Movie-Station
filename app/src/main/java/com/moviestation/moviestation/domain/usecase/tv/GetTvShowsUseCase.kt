package com.moviestation.moviestation.domain.usecase.tv

import com.moviestation.moviestation.comman.Resources
import com.moviestation.moviestation.data.remote.dto.TvShow
import com.moviestation.moviestation.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTvShowsUseCase @Inject constructor(private val tvRepository : TvRepository) {

   operator fun invoke (id: Int): Flow<Resources<List<TvShow>>> = flow {
       try {
           emit(Resources.Loading())
           val data = tvRepository.getTvShows(id).trendingTvShow
           emit(Resources.Success(data))
       } catch (e: HttpException) {
           emit(Resources.Error("an error occurred, please try again later"))
       } catch (e: IOException) {
           emit(Resources.Error("no internet connection"))
       }
   }
}