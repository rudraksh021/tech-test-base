package com.cleanarch.clean.navigation

sealed class Page(val route: String) {
    object Main : Page("main")
    object Feed : Page("feed")
    object Favorites : Page("favorites")
    object Search : Page("search")
    object MovieDetails : Page("movie_details") {
        const val MOVIE_ID: String = "movieId"
    }
}