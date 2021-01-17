package com.example.kotlin_rxjava.UI.landing

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.kotlin_rxjava.model.Movie
import com.example.kotlin_rxjava.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

import com.example.kotlin_rxjava.model.Result
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LandingViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel () {
    private val compositeDisposable = CompositeDisposable()
    private val _Movies = MutableLiveData<PagingData<Movie>>()
    val Movies : LiveData<PagingData<Movie>>
        get() = _Movies

    init {
        onGetMovieList()
    }

    override fun onCleared() {
        // To avoid memory leak
        compositeDisposable.clear()
        super.onCleared()
    }

    fun onRefresh() {
        onGetMovieList()
    }

    fun onGetMovieList() {
        compositeDisposable.add(
            movieRepository.getMovieList()
                .cachedIn(viewModelScope)
                .subscribe{
                    _Movies.value = it
                }
        )

    }
}
