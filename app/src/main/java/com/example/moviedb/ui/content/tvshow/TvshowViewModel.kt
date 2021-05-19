package com.example.moviedb.ui.content.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.vo.Resource

class TvshowViewModel(private val contentRepository: com.example.moviedb.core.domain.usecase.IContentUseCase) : ViewModel() {
    fun getTvshowsData(): LiveData<Resource<List<DataModel>>> =
        contentRepository.getTvshowList().asLiveData()

    fun searchTvShow(title: String): LiveData<List<DataModel>> =
        contentRepository.searchTvShow(title).asLiveData()
}