package com.cleanarch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cleanarch.domain.entities.MovieEntity

/**
 * Created by Aanal Shah on 05/12/2023
 */
@Entity(tableName = "movies")
data class MovieDbData(
    @PrimaryKey val id: Int,
    val description: String,
    val image: String,
    val title: String,
    val category: String,
)

fun MovieDbData.toDomain() = MovieEntity(
    id = id,
    image = image,
    description = description,
    title = title,
    category = category
)