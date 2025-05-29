package com.jardimtech.movie_db.domain.repository

import androidx.paging.PagingData
import com.jardimtech.movie_db.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
}
