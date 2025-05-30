package com.jardimtech.movie_db.movie_db.data.repository

import androidx.paging.testing.asSnapshot
import com.jardimtech.movie_db.data.api.MovieApi
import com.jardimtech.movie_db.data.model.MovieDto
import com.jardimtech.movie_db.data.model.MovieResponseDto
import com.jardimtech.movie_db.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MovieRepositoryImplTest {

    private lateinit var repository: MovieRepositoryImpl
    private lateinit var api: MovieApi

    @Before
    fun setup() {
        api = mock()
        repository = MovieRepositoryImpl(api)
    }

    @Test
    fun `getPopularMovies returns paging data`() = runTest {
        val response = MovieResponseDto(
            results = listOf(
                MovieDto(1, "Matrix", "/m1.jpg", "Neo lives"),
                MovieDto(2, "Inception", "/m2.jpg", "Dream inside dream")
            ),
            page = 1,
            totalPages = 1
        )

        whenever(api.getPopularMovies(1)).thenReturn(response)

        val result = repository.getPopularMovies()
        val snapshot = result.asSnapshot()

        assertEquals(2, snapshot.size)
        assertEquals("Matrix", snapshot[0].title)
        assertEquals("Inception", snapshot[1].title)
    }

    @Test
    fun `getMovieById should return the correct movie wrapped in Result`() = runTest {
        // Arrange
        val movieDto = MovieDto(42, "Interstellar", "/poster.jpg", "Space travel")
        whenever(api.getMovieById(42)).thenReturn(movieDto)

        // Act
        val result = repository.getMovieById(42)

        // Assert
        assertTrue(result.isSuccess)

        val movie = result.getOrNull()
        assertNotNull(movie)
        assertEquals(42, movie?.id)
        assertEquals("Interstellar", movie?.title)
        assertEquals("https://image.tmdb.org/t/p/w500/poster.jpg", movie?.posterUrl)
        assertEquals("Space travel", movie?.overview)
    }

    @Test
    fun `getMovieById should return failure when api throws exception`() = runTest {
        // Arrange
        whenever(api.getMovieById(999)).thenThrow(RuntimeException("API error"))

        // Act
        val result = repository.getMovieById(999)

        // Assert
        assertTrue(result.isFailure)
        assertEquals("API error", result.exceptionOrNull()?.message)
    }
}
