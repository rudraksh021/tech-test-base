package com.cleanarch.data.repository.movie.favorite

import androidx.paging.PagingSource
import com.cleanarch.data.entities.MovieDbData
import com.cleanarch.domain.util.Result

/**
 * Created by Aanal Shah on 05/12/2023
 */
interface FavoriteMoviesDataSource {

    interface Local {
        fun favoriteMovies(): PagingSource<Int, MovieDbData>
        suspend fun getFavoriteMovieIds(): Result<List<Int>>
        suspend fun addMovieToFavorite(movieId: Int)
        suspend fun removeMovieFromFavorite(movieId: Int)
        suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean>
    }
}
