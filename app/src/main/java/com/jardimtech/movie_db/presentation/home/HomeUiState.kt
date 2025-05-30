package com.jardimtech.movie_db.presentation.home

import androidx.paging.PagingData
import com.jardimtech.movie_db.domain.model.Movie
import kotlinx.coroutines.flow.Flow

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val movies: Flow<PagingData<Movie>>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}