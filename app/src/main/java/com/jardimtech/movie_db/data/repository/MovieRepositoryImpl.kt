package com.jardimtech.movie_db.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jardimtech.movie_db.data.api.MovieApi
import com.jardimtech.movie_db.data.paging.MoviePagingSource
import com.jardimtech.movie_db.domain.model.Movie
import com.jardimtech.movie_db.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviePagingSource(api) }
        ).flow
    }
}
