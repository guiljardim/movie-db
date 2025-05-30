package com.jardimtech.movie_db.presentation.detail

import com.jardimtech.movie_db.domain.model.Movie

sealed class MovieDetailUiState {
    object Loading : MovieDetailUiState()
    data class Success(val movie: Movie) : MovieDetailUiState()
    data class Error(val message: String) : MovieDetailUiState()
}
