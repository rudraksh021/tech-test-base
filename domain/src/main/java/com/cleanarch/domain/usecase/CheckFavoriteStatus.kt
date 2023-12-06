package com.cleanarch.domain.usecase

import com.cleanarch.domain.repository.MovieRepository
import com.cleanarch.domain.util.Result

/**
 * Created by Aanal Shah on 05/12/2023
 */
class CheckFavoriteStatus(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Boolean> = movieRepository.checkFavoriteStatus(movieId)
}