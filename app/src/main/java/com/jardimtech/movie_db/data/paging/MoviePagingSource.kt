package com.jardimtech.movie_db.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jardimtech.movie_db.data.api.MovieApi
import com.jardimtech.movie_db.domain.mapper.toDomain
import com.jardimtech.movie_db.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val api: MovieApi
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = api.getPopularMovies(page)
            val movies = response.results.map { it.toDomain() }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
}