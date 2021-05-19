package com.example.moviedb.di

import com.example.moviedb.core.domain.usecase.ContentInteractor
import com.example.moviedb.core.domain.usecase.IContentUseCase
import com.example.moviedb.ui.content.movie.MovieViewModel
import com.example.moviedb.ui.content.tvshow.TvshowViewModel
import com.example.moviedb.ui.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module

val useCaseModule = module {
    factory<IContentUseCase> {
        ContentInteractor(
            get()
        )
    }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvshowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}