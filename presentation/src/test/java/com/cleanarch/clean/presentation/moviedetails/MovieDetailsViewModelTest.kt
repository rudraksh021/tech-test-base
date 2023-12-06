package com.cleanarch.clean.presentation.moviedetails

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.cleanarch.clean.presentation.base.BaseViewModelTest
import com.cleanarch.clean.presentation.util.mock
import com.cleanarch.clean.ui.moviedetails.MovieDetailsViewModel
import com.cleanarch.clean.ui.navigationbar.Screen
import com.cleanarch.domain.entities.MovieEntity
import com.cleanarch.domain.usecase.AddMovieToFavorite
import com.cleanarch.domain.usecase.CheckFavoriteStatus
import com.cleanarch.domain.usecase.GetMovieDetails
import com.cleanarch.domain.usecase.RemoveMovieFromFavorite
import com.cleanarch.domain.util.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Aanal Shah on 05/12/2023
 **/
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest : BaseViewModelTest() {

    private var movieId: Int = 1413

    private val movie = MovieEntity(movieId, "title", "desc", "image", "category")

    @Mock
    lateinit var getMovieDetails: GetMovieDetails

    @Mock
    lateinit var checkFavoriteStatus: CheckFavoriteStatus

    @Mock
    lateinit var addMovieToFavorite: AddMovieToFavorite

    @Mock
    lateinit var removeMovieFromFavorite: RemoveMovieFromFavorite

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setUp() {
        `when`(savedStateHandle.get<Int>(Screen.MovieDetailsScreen.MOVIE_ID)).thenReturn(movieId)

        viewModel = MovieDetailsViewModel(
            getMovieDetails = getMovieDetails,
            checkFavoriteStatus = checkFavoriteStatus,
            removeMovieFromFavorite = removeMovieFromFavorite,
            addMovieToFavorite = addMovieToFavorite,
            savedStateHandle = savedStateHandle,
            dispatchers = coroutineRule.testDispatcherProvider
        )
    }

    @Test
    fun onInitialState_movieAvailable_showMovieDetails() = runTest {
        `when`(getMovieDetails(movieId)).thenReturn(Result.Success(movie))

        viewModel.onInitialState()

        viewModel.uiState.test {
            val emission = awaitItem()
            assertThat(emission.description).isEqualTo(movie.description)
            assertThat(emission.imageUrl).isEqualTo(movie.image)
            assertThat(emission.title).isEqualTo(movie.title)
            assertThat(emission.isFavorite).isFalse()
        }
    }

    @Test
    fun onInitialState_movieNotAvailable_doNothing() = runTest {
        `when`(getMovieDetails(movieId)).thenReturn(Result.Error(mock()))

        viewModel.onInitialState()

        viewModel.uiState.test {
            val emission = awaitItem()
            assertThat(emission).isEqualTo(MovieDetailsViewModel.MovieDetailsUiState())
        }
    }
}
