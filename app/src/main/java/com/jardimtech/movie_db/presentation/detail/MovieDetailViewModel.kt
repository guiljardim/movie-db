package com.jardimtech.movie_db.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jardimtech.movie_db.domain.usecase.GetMovieByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    fun loadMovie(id: Int) {
        viewModelScope.launch {
            _uiState.value = MovieDetailUiState.Loading

            val result = getMovieByIdUseCase(id)
            _uiState.value = result.fold(
                onSuccess = { movie -> MovieDetailUiState.Success(movie) },
                onFailure = { throwable ->
                    MovieDetailUiState.Error(
                        throwable.message ?: "Erro desconhecido ao carregar detalhes"
                    )
                }
            )
        }
    }
}

