package com.jardimtech.movie_db.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jardimtech.movie_db.data.api.MovieApi
import com.jardimtech.movie_db.data.paging.MoviePagingSource
import com.jardimtech.movie_db.domain.mapper.toDomain
import com.jardimtech.movie_db.domain.model.Movie
import com.jardimtech.movie_db.domain.repository.MovieRepository
import com.jardimtech.movie_db.utils.Constants.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(api) }
        ).flow
    }

    override suspend fun getMovieById(id: Int): Result<Movie> {
        return try {
            val dto = api.getMovieById(id)
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
