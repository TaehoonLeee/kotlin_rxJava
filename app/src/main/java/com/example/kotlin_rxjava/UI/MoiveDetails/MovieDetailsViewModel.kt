package com.example.kotlin_rxjava.UI.MoiveDetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.repository.MovieRepository

import com.example.kotlin_rxjava.model.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

class MovieDetailsViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _movie = MutableLiveData<Result<Movie>>()
    private val compositeDisposable = CompositeDisposable()

    val movie : LiveData<Result<Movie>>
        get() = _movie

    init {
        if (savedStateHandle.contains("movieID")) {
            val movieID = savedStateHandle.get<Long>("movieID")
            compositeDisposable.addAll(
                movieRepository.getMovieDetails(movieID!!)
                    .doOnSubscribe { _movie.value = Result.Loading(null) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( { movie -> _movie.value = Result.Success(movie)}, { e -> _movie.value = Result.Error(e.message!!) } )
            )
        }
        else {
            _movie.value = Result.Error("Failed to get arguments!!")
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}