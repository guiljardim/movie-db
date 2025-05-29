package com.jardimtech.movie_db.movie_db.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jardimtech.movie_db.data.api.MovieApi
import com.jardimtech.movie_db.data.model.MovieDto
import com.jardimtech.movie_db.data.model.MovieResponseDto
import com.jardimtech.movie_db.data.paging.MoviePagingSource
import com.jardimtech.movie_db.domain.model.Movie
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MoviePagingSourceTest {

    private val api = mock<MovieApi>()

    @Test
    fun `load returns page with data`() = runBlocking {
        val movies = listOf(MovieDto(1, "Batman", "/poster1.jpg", "desc"))
        val response = MovieResponseDto(movies, 1, 3)

        whenever(api.getPopularMovies(1)).thenReturn(response)

        val pagingSource = MoviePagingSource(api)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val expected = PagingSource.LoadResult.Page(
            data = listOf(
                Movie(
                    id = 1,
                    title = "Batman",
                    posterUrl = "https://image.tmdb.org/t/p/w500/poster1.jpg",
                    overview = "desc"
                )
            ),
            prevKey = null,
            nextKey = 2
        )

        assertEquals(expected, result)
    }
}
