package com.cleanarch.clean.entities

/**
 * Created by Aanal Shah on 05/12/2023
 */
sealed class MovieListItem {
    data class Movie(
        val id: Int,
        val imageUrl: String,
        val category: String,
    ) : MovieListItem()

    data class Separator(val category: String) : MovieListItem()
}
