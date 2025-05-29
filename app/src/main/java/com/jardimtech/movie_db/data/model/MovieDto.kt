package com.jardimtech.movie_db.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int
)

data class MovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("overview") val overview: String
)

