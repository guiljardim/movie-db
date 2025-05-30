package com.jardimtech.movie_db.movie_db.domain

import com.jardimtech.movie_db.domain.model.Movie
import com.jardimtech.movie_db.domain.repository.MovieRepository
import com.jardimtech.movie_db.domain.usecase.GetMovieByIdUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetMovieByIdUseCaseTest {

    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMovieByIdUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetMovieByIdUseCase(repository)
    }

    @Test
    fun `invoke should return movie from repository`() = runTest {
        val movie = Movie(
            id = 10,
            title = "Tenet",
            posterUrl = "https://image.tmdb.org/t/p/w500/tenet.jpg",
            overview = "Time-inversion thriller"
        )

        whenever(repository.getMovieById(10)).thenReturn(movie)

        val result = useCase(10)

        assertEquals("Tenet", result.title)
        assertEquals(10, result.id)
    }
}