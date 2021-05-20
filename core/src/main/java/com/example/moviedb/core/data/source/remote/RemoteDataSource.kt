package com.example.moviedb.core.data.source.remote

import com.example.moviedb.core.BuildConfig.API_KEY
import com.example.moviedb.core.data.source.remote.network.ApiService
import com.example.moviedb.core.data.source.remote.response.*
import com.example.moviedb.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovieList(): Flow<ApiResponse<List<MovieList>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val result = apiService.getListMovie(API_KEY)
                val dataArray = result.result
                if (dataArray != null) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(IO)
    }

    suspend fun getMovieDetail(movieId: Int): Flow<ApiResponse<MovieDetail>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val result = apiService.getDetailMovie(movieId, API_KEY)
                emit(ApiResponse.Success(result))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(IO)
    }

    suspend fun getTvshowList(): Flow<ApiResponse<List<TvshowList>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val result = apiService.getListTvshow(API_KEY)
                val dataArray = result.result
                if (dataArray != null) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(IO)
    }

    suspend fun getTvShowDetail(tvId: Int): Flow<ApiResponse<TvshowDetail>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val result = apiService.getTvshowDetail(tvId, API_KEY)
                emit(ApiResponse.Success(result))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(IO)
    }

    suspend fun getMovieVideo(movieId: Int): Flow<ApiResponse<Video>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val result = apiService.getVideoMovie(movieId, API_KEY)
                emit(ApiResponse.Success(result))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(IO)
    }

    suspend fun getTvshowVideo(tvId: Int): Flow<ApiResponse<Video>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val result = apiService.getVideoTvshow(tvId, API_KEY)
                emit(ApiResponse.Success(result))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(IO)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

    }
}