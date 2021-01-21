package com.example.kotlin_rxjava.UI.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_rxjava.model.Cast
import com.example.kotlin_rxjava.model.ProfileImage
import com.example.kotlin_rxjava.repository.MovieRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import com.example.kotlin_rxjava.model.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class PhotoViewModel @AssistedInject constructor(
    movieRepository: MovieRepository,
    @Assisted private val castId: Long
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _photo = MutableLiveData<Result<List<ProfileImage>>>()
    val photo : LiveData<Result<List<ProfileImage>>>
        get() = _photo

    init {
        compositeDisposable.addAll(
            movieRepository.getPersonImages(castId)
                .doOnSubscribe { _photo.value = Result.Loading(null) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { response -> _photo.value = Result.Success(response) }, { e -> _photo.value = Result.Error(e.message!!) } )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(castId : Long) : PhotoViewModel
    }

    companion object {
        fun provideFactory (
            assistedFactory : AssistedFactory,
            castId : Long
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(castId) as T
            }
        }
    }

}