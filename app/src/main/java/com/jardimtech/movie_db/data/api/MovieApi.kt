package com.jardimtech.movie_db.data.api

import com.jardimtech.movie_db.data.model.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): MovieResponseDto
}