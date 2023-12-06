package com.cleanarch.domain.usecase

import androidx.paging.PagingData
import com.cleanarch.domain.entities.MovieEntity
import com.cleanarch.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aanal Shah on 05/12/2023
 */
class SearchMovies(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(query: String, pageSize: Int): Flow<PagingData<MovieEntity>> = movieRepository.search(query, pageSize)
}
