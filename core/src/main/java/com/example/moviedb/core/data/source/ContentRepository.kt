package com.example.moviedb.core.data.source

import com.example.moviedb.core.data.NetworkBoundResource
import com.example.moviedb.core.data.source.local.LocalDataSource
import com.example.moviedb.core.data.source.local.entity.DataEntity
import com.example.moviedb.core.data.source.remote.ApiResponse
import com.example.moviedb.core.data.source.remote.RemoteDataSource
import com.example.moviedb.core.data.source.remote.response.*
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.domain.repository.IContentRepository
import com.example.moviedb.core.utils.*
import com.example.moviedb.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContentRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IContentRepository {

    override fun getMovieList(): Flow<Resource<List<DataModel>>> {
        return object : NetworkBoundResource<List<DataModel>, List<MovieList>>() {
            override fun loadFromDB(): Flow<List<DataModel>> {
                return localDataSource.getListMovie().map {
                    DataMapper.mapDataEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<DataModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieList>>> =
                remoteDataSource.getMovieList()

            override suspend fun saveCallResult(data: List<MovieList>) {
                val movieList = ArrayList<DataEntity>()
                for (response in data) {
                    val movie = DataEntity(
                        id = null,
                        dataId = response.id,
                        type = MOVIE,
                        title = response.title,
                        score = response.score.toString(),
                        imgPoster = response.imgPoster,
                    )
                    movieList.add(movie)
                }
                localDataSource.insertData(movieList)
            }
        }.asFlow()
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<DataModel>> {
        return object : NetworkBoundResource<DataModel, MovieDetail>() {
            override fun loadFromDB(): Flow<DataModel> {
                return localDataSource.getDetailMovie(movieId).map {
                    DataMapper.mapDetailData(it)
                }
            }

            override fun shouldFetch(data: DataModel?): Boolean =
                data?.description == null || data.releaseYear == null

            override suspend fun createCall(): Flow<ApiResponse<MovieDetail>> =
                remoteDataSource.getMovieDetail(movieId)


            override suspend fun saveCallResult(data: MovieDetail) {
                var genre = ""
                for (i in data.genre.indices) {
                    genre += data.genre[i].name
                    if (i != data.genre.size - 1) genre += DIVIDING_LINE
                }
                localDataSource.updateDataDetail(
                    data.id,
                    MOVIE,
                    data.description,
                    genre,
                    data.releaseDate,
                    data.member.toString(),
                    false
                )
            }

        }.asFlow()
    }

    override fun getTvshowList(): Flow<Resource<List<DataModel>>> {
        return object : NetworkBoundResource<List<DataModel>, List<TvshowList>>() {
            override fun loadFromDB(): Flow<List<DataModel>> {
                return localDataSource.getListTvShow().map {
                    DataMapper.mapDataEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<DataModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvshowList>>> =
                remoteDataSource.getTvshowList()

            override suspend fun saveCallResult(data: List<TvshowList>) {
                val tvShowList = ArrayList<DataEntity>()
                for (response in data) {
                    val tvShow = DataEntity(
                        id = null,
                        dataId = response.id,
                        type = TVSHOW,
                        title = response.title,
                        score = response.score.toString(),
                        imgPoster = response.imgPoster,
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertData(tvShowList)
            }

        }.asFlow()
    }

    override fun getTvshowDetail(tvId: Int): Flow<Resource<DataModel>> {
        return object : NetworkBoundResource<DataModel, TvshowDetail>() {
            override fun loadFromDB(): Flow<DataModel> {
                return localDataSource.getDetailTvShow(tvId).map {
                    DataMapper.mapDetailData(it)
                }
            }

            override fun shouldFetch(data: DataModel?): Boolean =
                data?.description == null || data.releaseYear == null

            override suspend fun createCall(): Flow<ApiResponse<TvshowDetail>> =
                remoteDataSource.getTvShowDetail(tvId)

            override suspend fun saveCallResult(data: TvshowDetail) {
                var genre = ""
                for (i in data.genre.indices) {
                    genre += data.genre[i].name
                    if (i != data.genre.size - 1) genre += DIVIDING_LINE
                }
                localDataSource.updateDataDetail(
                    data.id,
                    TVSHOW,
                    data.description,
                    genre,
                    data.releaseDate,
                    data.member.toString(),
                    false
                )
            }

        }.asFlow()
    }

    override fun getMovieVideo(movieId: Int): Flow<Resource<DataModel>> {
        return object : NetworkBoundResource<DataModel, Video>() {
            override fun loadFromDB(): Flow<DataModel> {
                return localDataSource.getVideoMovie(movieId).map {
                    DataMapper.mapDetailData(it)
                }
            }

            override fun shouldFetch(data: DataModel?): Boolean =
                data?.codeYoutube == null

            override suspend fun createCall(): Flow<ApiResponse<Video>> =
                remoteDataSource.getMovieVideo(movieId)

            override suspend fun saveCallResult(data: Video) {
                localDataSource.updateDetailCodeVideo(movieId, MOVIE, data.result!![0].youtubeCode)
            }

        }.asFlow()
    }

    override fun getTvshowVideo(tvId: Int): Flow<Resource<DataModel>> {
        return object : NetworkBoundResource<DataModel, Video>() {
            override fun loadFromDB(): Flow<DataModel> {
                return localDataSource.getVideoTvShow(tvId).map {
                    DataMapper.mapDetailData(it)
                }
            }

            override fun shouldFetch(data: DataModel?): Boolean =
                data?.codeYoutube == null

            override suspend fun createCall(): Flow<ApiResponse<Video>> =
                remoteDataSource.getTvshowVideo(tvId)

            override suspend fun saveCallResult(data: Video) {
                localDataSource.updateDetailCodeVideo(tvId, TVSHOW, data.result!![0].youtubeCode)
            }

        }.asFlow()
    }

    override fun getListFavoriteMovie(): Flow<List<DataModel>> {
        return localDataSource.getListFavoriteMovie().map {
            DataMapper.mapDataEntitiesToDomain(it)
        }
    }

    override fun getListFavoriteTvShow(): Flow<List<DataModel>> {
        return localDataSource.getListFavoriteTvShow().map {
            DataMapper.mapDataEntitiesToDomain(it)
        }
    }

    override fun searchMovie(title: String): Flow<List<DataModel>> {
        return localDataSource.searchMovie(title).map {
            DataMapper.mapDataEntitiesToDomain(it)
        }
    }

    override fun searchTvShow(title: String): Flow<List<DataModel>> {
        return localDataSource.searchTvShow(title).map {
            DataMapper.mapDataEntitiesToDomain(it)
        }
    }

    override fun setFavorite(data: DataModel) {
        val dataFavorite = DataMapper.mapDomainToDataEntities(data)
        appExecutors.diskIO().execute { localDataSource.setFavorite(dataFavorite) }
    }
}