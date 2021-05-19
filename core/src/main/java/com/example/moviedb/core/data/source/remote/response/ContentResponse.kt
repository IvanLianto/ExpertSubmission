package com.example.moviedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ContentResponse<T>(
    @field:SerializedName("results")
    val result: List<T>? = null,
)

data class MovieList(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val imgPoster: String? = null,

    @field:SerializedName("vote_average")
    val score: Double = 0.0
)

data class TvshowList(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val imgPoster: String? = null,

    @field:SerializedName("vote_average")
    val score: Double = 0.0
)

data class MovieDetail(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("overview")
    val description: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("poster_path")
    val imgPoster: String? = null,

    @field:SerializedName("vote_average")
    val score: Double = 0.0,

    @field:SerializedName("vote_count")
    val member: Int = 0,

    @field:SerializedName("genres")
    val genre: ArrayList<Genre>
)

data class TvshowDetail(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val title: String? = null,

    @field:SerializedName("overview")
    val description: String? = null,

    @field:SerializedName("first_air_date")
    val releaseDate: String? = null,

    @field:SerializedName("poster_path")
    val imgPoster: String? = null,

    @field:SerializedName("vote_average")
    val score: Double = 0.0,

    @field:SerializedName("vote_count")
    val member: Int = 0,

    @field:SerializedName("genres")
    val genre: ArrayList<Genre>
)

data class Genre(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val name: String? = null
)

data class Video(
    @field:SerializedName("results")
    val result: ArrayList<VideoList>? = null
)

data class VideoList(
    @field:SerializedName("key")
    val youtubeCode: String
)