package com.example.raphamovies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.raphamovies.data.model.Movie
import com.example.raphamovies.domain.usecase.DetailsUseCase
import com.example.raphamovies.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailsViewModel
constructor(private val detailsUseCase: DetailsUseCase) : BaseViewModel() {

    private val _onSuccessMovieById: MutableLiveData<Movie> = MutableLiveData()
    val onSuccessMovieById: LiveData<Movie>
        get() = _onSuccessMovieById


    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            callApi(
                suspend { detailsUseCase.getMovieById(movieId) },
                onSuccess = {
                    _onSuccessMovieById.postValue(it as? Movie)
                }
            )
        }
    }
}