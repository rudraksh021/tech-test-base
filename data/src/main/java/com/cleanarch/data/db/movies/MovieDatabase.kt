package com.cleanarch.data.db.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cleanarch.data.db.favoritemovies.FavoriteMovieDao
import com.cleanarch.data.entities.FavoriteMovieDbData
import com.cleanarch.data.entities.MovieDbData
import com.cleanarch.data.entities.MovieRemoteKeyDbData

/**
 * Created by Aanal Shah on 05/12/2023
 */
@Database(
    entities = [MovieDbData::class, FavoriteMovieDbData::class, MovieRemoteKeyDbData::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeyDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}