package com.example.moviedb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.domain.usecase.IContentUseCase
import com.example.moviedb.core.vo.Resource

class DetailViewModel(private val contentRepository: IContentUseCase) : ViewModel() {
    fun getDetailMovieById(id: Int): LiveData<Resource<DataModel>> =
        contentRepository.getMovieDetail(id).asLiveData()

    fun getDetailTvshowId(id: Int): LiveData<Resource<DataModel>> =
        contentRepository.getTvshowDetail(id).asLiveData()

    fun getVideoMovie(id: Int): LiveData<Resource<DataModel>> =
        contentRepository.getMovieVideo(id).asLiveData()

    fun getVideoTvshow(id: Int): LiveData<Resource<DataModel>> =
        contentRepository.getTvshowVideo(id).asLiveData()

    fun setFavorite(data: DataModel) = contentRepository.setFavorite(data)
}