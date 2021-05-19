package com.example.moviedb.core.data.source.local.room

import androidx.room.*
import com.example.moviedb.core.data.source.local.entity.DataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {
    @Query("SELECT * FROM tb_data WHERE type = 'MOVIE'")
    fun getListMovie(): Flow<List<DataEntity>>

    @Query("SELECT * FROM tb_data WHERE type = 'TVSHOW'")
    fun getListTvShow(): Flow<List<DataEntity>>

    @Query("SELECT * FROM tb_data WHERE type = 'MOVIE' AND favorite = 1")
    fun getListFavoriteMovie(): Flow<List<DataEntity>>

    @Query("SELECT * FROM tb_data WHERE type = 'TVSHOW' AND favorite = 1")
    fun getListFavoriteTvShow(): Flow<List<DataEntity>>

    @Query("SELECT * FROM tb_data WHERE dataId = :id AND type = 'MOVIE'")
    fun getDetailMovieById(id: Int): Flow<DataEntity>

    @Query("SELECT * FROM tb_data WHERE dataId = :id AND type = 'TVSHOW'")
    fun getDetailTvShowById(id: Int): Flow<DataEntity>

    @Query("SELECT dataId, favorite, codeYoutube FROM tb_data WHERE dataId = :id AND type = 'MOVIE'")
    fun getVideoMovie(id: Int): Flow<DataEntity>

    @Query("SELECT dataId, favorite, codeYoutube FROM tb_data WHERE dataId = :id AND type = 'TVSHOW'")
    fun getVideoTvShow(id: Int): Flow<DataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DataEntity::class)
    suspend fun insertData(data: List<DataEntity>)

    @Update(entity = DataEntity::class)
    fun updateData(data: DataEntity)

    @Query("UPDATE tb_data SET description = :description, genre = :genre, year = :releaseYear, member = :member, favorite = :favorite  WHERE dataId = :id AND type = :type")
    suspend fun updateDataDetail(
        id: Int,
        type: String,
        description: String?,
        genre: String,
        releaseYear: String?,
        member: String,
        favorite: Boolean
    )

    @Query("UPDATE tb_data SET codeYoutube = :codeId WHERE dataId = :id AND type = :type")
    suspend fun updateDataVideo(id: Int, type: String, codeId: String)

    @Query("SELECT * FROM tb_data WHERE title LIKE :title AND type = 'MOVIE'")
    fun searchMovie(title: String): Flow<List<DataEntity>>

    @Query("SELECT * FROM tb_data WHERE title LIKE :title AND type = 'TVSHOW'")
    fun searchTvShow(title: String): Flow<List<DataEntity>>
}