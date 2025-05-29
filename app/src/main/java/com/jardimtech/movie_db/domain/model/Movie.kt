package com.jardimtech.movie_db.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val overview: String
)
