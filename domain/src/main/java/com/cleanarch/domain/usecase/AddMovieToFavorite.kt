package com.cleanarch.domain.usecase

import com.cleanarch.domain.repository.MovieRepository

/**
 * Created by Aanal Shah on 05/12/2023
 */
class AddMovieToFavorite(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) = movieRepository.addMovieToFavorite(movieId)
}