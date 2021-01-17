package com.example.kotlin_rxjava.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.model.Movies
import com.example.kotlin_rxjava.network.MovieService
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val moviePagingSource: MoviePagingSource
){
    fun getMovieList() : Flowable<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { moviePagingSource }
        ).flowable
    }

    fun getMovieDetails(movieID : Long) : Single<Movie> {
        return movieService.getMovie(movieID)
            .subscribeOn(Schedulers.io())
    }
}