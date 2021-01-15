package com.example.kotlin_rxjava.network

import com.example.kotlin_rxjava.model.Movies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MovieService {

    @GET("trending/all/day?api_key=72a65ce9c10f2d0fe0fd232119fb06be")
    fun getMovie(): Single<Movies>
}