package com.example.kotlin_rxjava.repository

import com.example.kotlin_rxjava.model.Movies
import com.example.kotlin_rxjava.network.MovieService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieService: MovieService
){
    fun getMovie() : Single<Movies> {
        return movieService.getMovie()
            .subscribeOn(Schedulers.io())
    }
}