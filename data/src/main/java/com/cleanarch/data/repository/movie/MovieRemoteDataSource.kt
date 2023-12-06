package com.cleanarch.data.repository.movie

import com.cleanarch.data.api.MovieApi
import com.cleanarch.data.entities.toDomain
import com.cleanarch.domain.entities.MovieEntity
import com.cleanarch.domain.util.Result

/**
 * Created by Aanal Shah on 05/12/2023
 */
class MovieRemoteDataSource(
    private val movieApi: MovieApi
) : MovieDataSource.Remote {

    override suspend fun getMovies(page: Int, limit: Int): Result<List<MovieEntity>> = try {
        val result = movieApi.getMovies(page, limit)
        Result.Success(result.map { it.toDomain() })
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun getMovies(movieIds: List<Int>): Result<List<MovieEntity>> = try {
        val result = movieApi.getMovies(movieIds)
        Result.Success(result.map { it.toDomain() })
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun getMovie(movieId: Int): Result<MovieEntity> = try {
        val result = movieApi.getMovie(movieId)
        Result.Success(result.toDomain())
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun search(query: String, page: Int, limit: Int): Result<List<MovieEntity>> = try {
        val result = movieApi.search(query, page, limit)
        Result.Success(result.map { it.toDomain() })
    } catch (e: Exception) {
        Result.Error(e)
    }
}
