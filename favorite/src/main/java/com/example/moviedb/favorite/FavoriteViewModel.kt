package com.example.moviedb.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.domain.usecase.IContentUseCase

class FavoriteViewModel(private val contentRepository: IContentUseCase) : ViewModel() {
    fun getListFavoriteMovie(): LiveData<List<DataModel>> =
        contentRepository.getListFavoriteMovie().asLiveData()

    fun getListFavoriteTvshow(): LiveData<List<DataModel>> =
        contentRepository.getListFavoriteTvShow().asLiveData()
}