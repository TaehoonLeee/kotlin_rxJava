package com.example.kotlin_rxjava.network

import com.example.kotlin_rxjava.model.Movies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MovieService {

    @GET("trending/all/day")
    fun getMovie(): Single<Movies>
}