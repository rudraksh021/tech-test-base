package com.cleanarch.clean.mapper

import com.cleanarch.clean.entities.MovieListItem
import com.cleanarch.domain.entities.MovieEntity

/**
 * Created by Aanal Shah on 05/12/2023
 */

fun MovieEntity.toPresentation() = MovieListItem.Movie(
    id = id,
    imageUrl = image,
    category = category
)