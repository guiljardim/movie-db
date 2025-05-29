package com.jardimtech.movie_db.movie_db.domain


import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.jardimtech.movie_db.domain.model.Movie
import com.jardimtech.movie_db.domain.repository.MovieRepository
import com.jardimtech.movie_db.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetPopularMoviesUseCaseTest {

    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetPopularMoviesUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetPopularMoviesUseCase(repository)
    }

    @Test
    fun `should emit paging data when repository returns data`() = runTest {
        val movies = listOf(
            Movie(1, "Batman", "url1", "desc"),
            Movie(2, "Superman", "url2", "desc2")
        )

        whenever(repository.getPopularMovies()).thenReturn(
            flowOf(PagingData.from(movies))
        )

        val resultList = useCase().asSnapshot()

        assertEquals(2, resultList.size)
        assertEquals("Batman", resultList[0].title)
    }

}