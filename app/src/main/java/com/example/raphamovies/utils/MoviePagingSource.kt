package com.example.raphamovies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.raphamovies.data.model.Movie
import com.example.raphamovies.data.model.MovieResult
import com.example.raphamovies.domain.repository.HomeRepository
import com.example.raphamovies.domain.usecase.HomeUseCase
import com.example.raphamovies.utils.ResponseApi

class MoviePagingSource(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val page: Int = params.key ?: 1
            val response = callPopularMoviesApi(page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else -1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun callPopularMoviesApi(page: Int): List<Movie> {
        return when (
            val response = homeRepository.getPopularMovies(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? MovieResult
                homeUseCase.setupMoviesList(list)
            }
            is ResponseApi.Error -> {
                listOf()
            }
        }

    }
}