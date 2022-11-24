package com.creativelab.moviemvvm.data.api

import com.creativelab.moviemvvm.data.vo.MovieDetails
import com.creativelab.moviemvvm.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    // https://api.themoviedb.org/3/movie/popular?api_key=4440dd3cf89a86caafb289081b0cb206
    // https://api.themoviedb.org/3/movie/882598?api_key=4440dd3cf89a86caafb289081b0cb206
    // https://api.themoviedb.org/3/

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>

}