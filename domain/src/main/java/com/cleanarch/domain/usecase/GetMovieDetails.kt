package com.cleanarch.domain.usecase

import com.cleanarch.domain.entities.MovieEntity
import com.cleanarch.domain.repository.MovieRepository
import com.cleanarch.domain.util.Result

/**
 * Created by Aanal Shah on 05/12/2023
 **/
class GetMovieDetails(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<MovieEntity> = movieRepository.getMovie(movieId)
}
