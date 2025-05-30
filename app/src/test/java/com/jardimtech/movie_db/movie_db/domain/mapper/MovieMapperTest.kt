package com.jardimtech.movie_db.movie_db.domain.mapper

import com.jardimtech.movie_db.data.model.MovieDto
import com.jardimtech.movie_db.domain.mapper.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieMapperTest {

    @Test
    fun `toDomain should map MovieDto to Movie correctly with posterPath`() {
        val dto = MovieDto(
            id = 1,
            title = "The Matrix",
            posterPath = "/matrix.jpg",
            overview = "Neo discovers the truth"
        )

        val domain = dto.toDomain()

        assertEquals(1, domain.id)
        assertEquals("The Matrix", domain.title)
        assertEquals("https://image.tmdb.org/t/p/w500/matrix.jpg", domain.posterUrl)
        assertEquals("Neo discovers the truth", domain.overview)
    }

    @Test
    fun `toDomain should handle null posterPath`() {
        val dto = MovieDto(
            id = 2,
            title = "No Poster",
            posterPath = null,
            overview = "Missing image"
        )

        val domain = dto.toDomain()

        assertEquals(2, domain.id)
        assertEquals("No Poster", domain.title)
        assertEquals(null, domain.posterUrl)
        assertEquals("Missing image", domain.overview)
    }
}
