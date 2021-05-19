package com.example.moviedb.core.domain.repository

import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IContentRepository {
    fun getMovieList(): Flow<Resource<List<DataModel>>>

    fun getMovieDetail(movieId: Int): Flow<Resource<DataModel>>

    fun getTvshowList(): Flow<Resource<List<DataModel>>>

    fun getTvshowDetail(tvId: Int): Flow<Resource<DataModel>>

    fun getMovieVideo(movieId: Int): Flow<Resource<DataModel>>

    fun getTvshowVideo(tvId: Int): Flow<Resource<DataModel>>

    fun getListFavoriteMovie(): Flow<List<DataModel>>

    fun getListFavoriteTvShow(): Flow<List<DataModel>>

    fun searchMovie(title: String): Flow<List<DataModel>>

    fun searchTvShow(title: String): Flow<List<DataModel>>

    fun setFavorite(data: DataModel)
}