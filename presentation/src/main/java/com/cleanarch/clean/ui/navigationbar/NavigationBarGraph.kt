package com.cleanarch.clean.ui.navigationbar

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cleanarch.clean.ui.favorites.FavoritesPage
import com.cleanarch.clean.ui.favorites.FavoritesViewModel
import com.cleanarch.clean.ui.feed.FeedPage
import com.cleanarch.clean.ui.feed.FeedViewModel
import com.cleanarch.clean.ui.main.MainRouter
import com.cleanarch.clean.navigation.Page
import com.cleanarch.clean.util.composableHorizontalSlide

@Composable
fun NavigationBarGraph(navController: NavHostController, mainRouter: MainRouter) {
    NavHost(navController = navController, startDestination = Page.Feed.route) {
        composableHorizontalSlide(route = Page.Feed.route) {
            val viewModel = hiltViewModel<FeedViewModel>()
            FeedPage(
                mainRouter = mainRouter,
                viewModel = viewModel,
            )
        }
        composableHorizontalSlide(route = Page.Favorites.route) {
            val viewModel = hiltViewModel<FavoritesViewModel>()
            FavoritesPage(
                mainRouter = mainRouter,
                viewModel = viewModel,
            )
        }
    }
}