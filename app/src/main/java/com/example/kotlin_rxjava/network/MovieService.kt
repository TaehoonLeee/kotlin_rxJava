package com.example.kotlin_rxjava.network

import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.model.Movies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("trending/all/day")
    fun getMovieList(): Single<Movies>

    @GET("movie/{movieID}")
    fun getMovie(@Path("movieID")movieID : Long): Single<Movie>
}