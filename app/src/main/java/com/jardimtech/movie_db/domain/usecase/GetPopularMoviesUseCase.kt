package com.jardimtech.movie_db.domain.usecase

import androidx.paging.PagingData
import com.jardimtech.movie_db.domain.model.Movie
import com.jardimtech.movie_db.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies()
    }
}