package com.cleanarch.data.mapper

import com.cleanarch.data.entities.MovieDbData
import com.cleanarch.domain.entities.MovieEntity

/**
 * Created by Aanal Shah on 05/12/2023
 **/

fun MovieEntity.toDbData() = MovieDbData(
    id = id,
    image = image,
    description = description,
    title = title,
    category = category
)
