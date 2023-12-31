package com.cleanarch.clean.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cleanarch.clean.ui.moviedetails.MovieDetailsPage
import com.cleanarch.clean.ui.moviedetails.MovieDetailsViewModel
import com.cleanarch.clean.navigation.Page
import com.cleanarch.clean.ui.navigationbar.NavigationBarScreen
import com.cleanarch.clean.ui.search.SearchPage
import com.cleanarch.clean.ui.search.SearchViewModel
import com.cleanarch.clean.util.composableHorizontalSlide

@Composable
fun MainGraph(
    mainNavController: NavHostController,
    darkMode: Boolean,
    onThemeUpdated: () -> Unit
) {
    NavHost(navController = mainNavController, startDestination = Page.Main.route) {
        composableHorizontalSlide(route = Page.Main.route) {
            val nestedNavController = rememberNavController()
            NavigationBarScreen(
                mainRouter = MainRouter(mainNavController),
                darkMode = darkMode,
                onThemeUpdated = onThemeUpdated,
                nestedNavController = nestedNavController
            )
        }

        composableHorizontalSlide(route = Page.Search.route) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchPage(
                mainNavController = mainNavController,
                viewModel = viewModel,
            )
        }

        composableHorizontalSlide(
            route = "${Page.MovieDetails.route}/{${Page.MovieDetails.MOVIE_ID}}",
            arguments = listOf(
                navArgument(Page.MovieDetails.MOVIE_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) {
            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            MovieDetailsPage(
                mainNavController = mainNavController,
                viewModel = viewModel,
            )
        }
    }
}