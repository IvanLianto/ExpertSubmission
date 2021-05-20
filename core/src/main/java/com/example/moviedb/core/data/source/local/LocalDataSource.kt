package com.example.moviedb.core.data.source.local

import com.example.moviedb.core.data.source.local.entity.DataEntity
import com.example.moviedb.core.data.source.local.room.ContentDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val contentDao: ContentDao) {

    fun getListMovie(): Flow<List<DataEntity>> = contentDao.getListMovie()

    fun getListFavoriteMovie(): Flow<List<DataEntity>> =
        contentDao.getListFavoriteMovie()

    fun getDetailMovie(id: Int): Flow<DataEntity> = contentDao.getDetailMovieById(id)

    fun getListTvShow(): Flow<List<DataEntity>> = contentDao.getListTvShow()

    fun getListFavoriteTvShow(): Flow<List<DataEntity>> =
        contentDao.getListFavoriteTvShow()

    fun getDetailTvShow(id: Int): Flow<DataEntity> = contentDao.getDetailTvShowById(id)

    fun getVideoMovie(id: Int): Flow<DataEntity> = contentDao.getVideoMovie(id)

    fun getVideoTvShow(id: Int): Flow<DataEntity> = contentDao.getVideoTvShow(id)

    suspend fun insertData(data: List<DataEntity>) = contentDao.insertData(data)

    fun searchMovie(title: String): Flow<List<DataEntity>> = contentDao.searchMovie("%$title%")

    fun searchTvShow(title: String): Flow<List<DataEntity>> = contentDao.searchTvShow("%$title%")

    fun setFavorite(data: DataEntity) {
        data.favorite = !data.favorite
        contentDao.updateData(data)
    }

    suspend fun updateDataDetail(
        id: Int,
        type: String,
        description: String?,
        genre: String,
        releaseYear: String?,
        member: String,
        favorite: Boolean
    ) =
        contentDao.updateDataDetail(id, type, description, genre, releaseYear, member, favorite)

    suspend fun updateDetailCodeVideo(id: Int, type: String, codeId: String) {
        contentDao.updateDataVideo(id, type, codeId)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

    }

}