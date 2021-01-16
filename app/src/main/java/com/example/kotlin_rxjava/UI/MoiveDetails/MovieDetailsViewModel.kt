package com.example.kotlin_rxjava.UI.MoiveDetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.repository.MovieRepository

import com.example.kotlin_rxjava.model.Result
import timber.log.Timber

class MovieDetailsViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _movie = MutableLiveData<Result<Movie>>()

    val movie : LiveData<Result<Movie>>
        get() = _movie

    init {
        if (savedStateHandle.contains("movieID")) {
            val movieID = savedStateHandle.get<Long>("movieID")
            Timber.i("movieID : $movieID")
        }
        else {
            _movie.value = Result.Error("Failed to get arguments!!")
        }
    }
}