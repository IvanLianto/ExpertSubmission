package com.example.moviedb.ui.content.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.domain.usecase.IContentUseCase
import com.example.moviedb.core.vo.Resource

class MovieViewModel(private val contentRepository: IContentUseCase) : ViewModel() {
    fun getMoviesData(): LiveData<Resource<List<DataModel>>> =
        contentRepository.getMovieList().asLiveData()

    fun searchMovie(title: String): LiveData<List<DataModel>> =
        contentRepository.searchMovie(title).asLiveData()
}