package com.cleanarch.clean.ui.moviedetails

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import com.cleanarch.clean.ui.base.BaseViewModel
import com.cleanarch.clean.navigation.Page
import com.cleanarch.data.util.DispatchersProvider
import com.cleanarch.domain.entities.MovieEntity
import com.cleanarch.domain.usecase.AddMovieToFavorite
import com.cleanarch.domain.usecase.CheckFavoriteStatus
import com.cleanarch.domain.usecase.GetMovieDetails
import com.cleanarch.domain.usecase.RemoveMovieFromFavorite
import com.cleanarch.domain.util.Result
import com.cleanarch.domain.util.getResult
import com.cleanarch.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by Aanal Shah on 05/12/2023
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails,
    private val checkFavoriteStatus: CheckFavoriteStatus,
    private val addMovieToFavorite: AddMovieToFavorite,
    private val removeMovieFromFavorite: RemoveMovieFromFavorite,
    savedStateHandle: SavedStateHandle,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    data class MovieDetailsUiState(
        val title: String = "",
        val description: String = "",
        val imageUrl: String = "",
        val isFavorite: Boolean = false,
    )

    private val _uiState: MutableStateFlow<MovieDetailsUiState> = MutableStateFlow(MovieDetailsUiState())
    val uiState = _uiState.asStateFlow()

    private var movieId: Int

    init {
        movieId = savedStateHandle[Page.MovieDetails.MOVIE_ID] ?: 0
        onInitialState()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onInitialState() = launchOnMainImmediate {
        val isFavorite = async { checkFavoriteStatus(movieId).getResult({ favoriteResult -> favoriteResult.data }, { false }) }
        getMovieById(movieId).onSuccess {
            _uiState.value = MovieDetailsUiState(
                title = it.title,
                description = it.description,
                imageUrl = it.image,
                isFavorite = isFavorite.await()
            )
        }
    }

    fun onFavoriteClicked() = launchOnMainImmediate {
        checkFavoriteStatus(movieId).onSuccess { isFavorite ->
            if (isFavorite) removeMovieFromFavorite(movieId) else addMovieToFavorite(movieId)
            _uiState.update { it.copy(isFavorite = !isFavorite) }
        }
    }

    private suspend fun getMovieById(movieId: Int): Result<MovieEntity> = getMovieDetails(movieId)

    private suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean> = checkFavoriteStatus.invoke(movieId)
}