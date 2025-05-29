package com.jardimtech.movie_db.domain.mapper

import com.jardimtech.movie_db.data.model.MovieDto
import com.jardimtech.movie_db.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        overview = overview
    )
}