package com.example.kotlin_rxjava.network

import com.example.kotlin_rxjava.model.Cast
import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.model.Movies
import com.example.kotlin_rxjava.model.Profile
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    // https://api.themoviedb.org/3/trending/all/day?api_key=<<api_key>>
    @GET("trending/all/day")
    fun getMovieList(@Query("page")page : Int): Single<Movies>

    // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>
    @GET("movie/{movieID}?append_to_response=credits")
    fun getMovie(@Path("movieID")movieID : Long): Single<Movie>

    // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>
    @GET("person/{id}")
    fun getCastDetails(@Path("id")castId : Long): Single<Cast>

    @GET("person/{id}/images")
    fun getPersonImages(@Path("id")castId : Long): Single<Profile>
}