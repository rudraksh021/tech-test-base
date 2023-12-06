package com.cleanarch.data.api

import com.cleanarch.data.entities.MovieData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Aanal Shah on 05/12/2023
 */
interface MovieApi {

    @GET("/movies?&_sort=category,id")
    suspend fun getMovies(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): List<MovieData>

    @GET("/movies")
    suspend fun getMovies(@Query("id") movieIds: List<Int>): List<MovieData>

    @GET("/movies/{id}")
    suspend fun getMovie(@Path("id") movieId: Int): MovieData

    @GET("/movies")
    suspend fun search(
        @Query("q") query: String,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): List<MovieData>
}