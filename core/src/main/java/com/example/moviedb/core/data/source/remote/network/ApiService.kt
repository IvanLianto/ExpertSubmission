package com.example.moviedb.core.data.source.remote.network

import com.example.moviedb.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getListMovie(@Query("api_key") apiKey: String): ContentResponse<MovieList>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetail

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Video

    @GET("tv/on_the_air")
    suspend fun getListTvshow(@Query("api_key") apiKey: String): ContentResponse<TvshowList>

    @GET("tv/{tv_id}")
    suspend fun getTvshowDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): TvshowDetail

    @GET("tv/{tv_id}/videos")
    suspend fun getVideoTvshow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Video

}