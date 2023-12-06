package com.cleanarch.clean.ui.favorites

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.cleanarch.clean.entities.MovieListItem
import com.cleanarch.clean.mapper.toPresentation
import com.cleanarch.clean.ui.base.BaseViewModel
import com.cleanarch.clean.util.singleSharedFlow
import com.cleanarch.data.util.DispatchersProvider
import com.cleanarch.domain.usecase.GetFavoriteMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Aanal Shah on 05/12/2023
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getFavoriteMovies: GetFavoriteMovies,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    data class FavoriteUiState(
        val isLoading: Boolean = true,
        val noDataAvailable: Boolean = false
    )

    sealed class NavigationState {
        data class MovieDetails(val movieId: Int) : NavigationState()
    }

    val movies: Flow<PagingData<MovieListItem>> = getFavoriteMovies(30)
        .map { pagingData ->
            pagingData.map { movieEntity ->
                movieEntity.toPresentation() as MovieListItem
            }
        }.cachedIn(viewModelScope)

    private val _uiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationState: MutableSharedFlow<NavigationState> = singleSharedFlow()
    val navigationState = _navigationState.asSharedFlow()

    fun onMovieClicked(movieId: Int) =
        _navigationState.tryEmit(NavigationState.MovieDetails(movieId))

    fun onLoadStateUpdate(loadState: CombinedLoadStates, itemCount: Int) {
        val showLoading = loadState.refresh is LoadState.Loading
        val showNoData = loadState.append.endOfPaginationReached && itemCount < 1

        _uiState.update {
            it.copy(
                isLoading = showLoading,
                noDataAvailable = showNoData
            )
        }
    }
}
