package com.cleanarch.domain.entities

/**
 * Created by Aanal Shah on 05/12/2023
 */
data class MovieEntity(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val category: String
)