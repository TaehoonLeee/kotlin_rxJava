package com.example.kotlin_rxjava.UI.landing

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

import com.example.kotlin_rxjava.model.Result

class LandingViewModel @ViewModelInject constructor(
    movieRepository: MovieRepository
) : ViewModel () {
    private val _Movies = MutableLiveData<Result<List<Movie>>>()
    val Movies : LiveData<Result<List<Movie>>>
        get() = _Movies

    init {
        movieRepository.getMovie()
            .doOnSubscribe { _Movies.value = Result.Loading(null) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies -> _Movies.value = Result.Success(movies.results) },
                {
                    e -> Timber.e(e)
                    _Movies.value = Result.Error(e.message!!, null)
                })
    }

}
