package com.cleanarch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Aanal Shah on 05/12/2023
 */
@Entity(tableName = "favorite_movies")
data class FavoriteMovieDbData(
    @PrimaryKey val movieId: Int
)