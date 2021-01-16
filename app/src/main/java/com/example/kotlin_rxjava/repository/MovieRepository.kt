package com.example.kotlin_rxjava.repository

import com.example.kotlin_rxjava.model.Movie
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
    fun getMovieList() : Single<Movies> {
        return movieService.getMovieList()
            .subscribeOn(Schedulers.io())
    }

    fun getMovieDetails(movieID : Long) : Single<Movie> {
        return movieService.getMovie(movieID)
            .subscribeOn(Schedulers.io())
    }
}