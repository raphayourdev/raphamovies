package com.example.raphamovies.di

import com.example.raphamovies.MoviePagingSource
import com.example.raphamovies.domain.repository.DetailsRepository
import com.example.raphamovies.domain.repository.HomeRepository
import com.example.raphamovies.domain.repository.SearchRepository
import com.example.raphamovies.domain.usecase.DetailsUseCase
import com.example.raphamovies.domain.usecase.HomeUseCase
import com.example.raphamovies.domain.usecase.SearchUseCase
import com.example.raphamovies.presentation.viewmodel.DetailsViewModel
import com.example.raphamovies.presentation.viewmodel.HomeViewModel
import com.example.raphamovies.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single { HomeRepository() }
        single { SearchRepository() }
        factory { DetailsRepository() }

        single { DetailsUseCase(repository = get()) }
        single { HomeUseCase(repository = get()) }
        single { SearchUseCase(repository = get()) }

        single {
            MoviePagingSource(
                homeRepository = get(),
                homeUseCase = get()
            )
        }

        viewModel { DetailsViewModel(detailsUseCase = get()) }
        viewModel { HomeViewModel(homeUseCase = get(), homeRepository = get()) }
        viewModel { SearchViewModel(searchUseCase = get()) }
    }
}
