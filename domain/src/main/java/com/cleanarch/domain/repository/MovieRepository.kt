package com.cleanarch.domain.repository

import androidx.paging.PagingData
import com.cleanarch.domain.entities.MovieEntity
import com.cleanarch.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aanal Shah on 05/12/2023
 */
interface MovieRepository {
    fun movies(pageSize: Int): Flow<PagingData<MovieEntity>>
    fun favoriteMovies(pageSize: Int): Flow<PagingData<MovieEntity>>
    fun search(query: String, pageSize: Int): Flow<PagingData<MovieEntity>>
    suspend fun getMovie(movieId: Int): Result<MovieEntity>
    suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean>
    suspend fun addMovieToFavorite(movieId: Int)
    suspend fun removeMovieFromFavorite(movieId: Int)
    suspend fun sync(): Boolean
}