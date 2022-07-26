package com.example.raphamovies.domain.repository

import com.example.raphamovies.network.ApiService
import com.example.raphamovies.presentation.base.BaseRepository
import com.example.raphamovies.utils.ResponseApi


class HomeRepository : BaseRepository() {
    suspend fun getPopularMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getPopularMovies(page)
        }
    }
}