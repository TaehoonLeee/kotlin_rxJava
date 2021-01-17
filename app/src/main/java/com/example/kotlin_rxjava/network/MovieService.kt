package com.example.kotlin_rxjava.network

import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.model.Movies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    // https://api.themoviedb.org/3/trending/all/day?api_key=<<api_key>>
    @GET("trending/all/day")
    fun getMovieList(): Single<Movies>

    // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>
    @GET("movie/{movieID}")
    fun getMovie(@Path("movieID")movieID : Long): Single<Movie>
}