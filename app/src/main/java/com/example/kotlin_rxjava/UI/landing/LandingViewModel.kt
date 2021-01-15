package com.example.kotlin_rxjava.UI.landing

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

class LandingViewModel @ViewModelInject constructor(
    movieRepository: MovieRepository
) : ViewModel () {
    private val _Movies = MutableLiveData<List<Movie>>()
    val Movies : LiveData<List<Movie>>
        get() = _Movies

    init {
        movieRepository.getMovie()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies -> _Movies.value = movies.results }, { e -> Timber.e(e) })
    }

}
