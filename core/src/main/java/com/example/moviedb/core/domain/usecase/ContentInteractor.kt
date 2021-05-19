package com.example.moviedb.core.domain.usecase

import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.domain.repository.IContentRepository
import com.example.moviedb.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class ContentInteractor(private val contentRepository: IContentRepository) : IContentUseCase {
    override fun getMovieList(): Flow<Resource<List<DataModel>>> = contentRepository.getMovieList()

    override fun getMovieDetail(movieId: Int): Flow<Resource<DataModel>> = contentRepository.getMovieDetail(movieId)

    override fun getTvshowList(): Flow<Resource<List<DataModel>>> = contentRepository.getTvshowList()

    override fun getTvshowDetail(tvId: Int): Flow<Resource<DataModel>> = contentRepository.getTvshowDetail(tvId)

    override fun getMovieVideo(movieId: Int): Flow<Resource<DataModel>> = contentRepository.getMovieVideo(movieId)

    override fun getTvshowVideo(tvId: Int): Flow<Resource<DataModel>> = contentRepository.getTvshowVideo(tvId)

    override fun getListFavoriteMovie(): Flow<List<DataModel>> = contentRepository.getListFavoriteMovie()

    override fun getListFavoriteTvShow(): Flow<List<DataModel>> = contentRepository.getListFavoriteTvShow()

    override fun searchMovie(title: String): Flow<List<DataModel>> = contentRepository.searchMovie(title)

    override fun searchTvShow(title: String): Flow<List<DataModel>> = contentRepository.searchTvShow(title)

    override fun setFavorite(data: DataModel) = contentRepository.setFavorite(data)

}