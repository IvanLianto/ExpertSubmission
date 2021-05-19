package com.example.moviedb.core.domain.model

data class DataModel(
        var id: Int? = null,
        var dataId: Int,
        var type: String? = null,
        var title: String? = null,
        var description: String? = null,
        var genre: String? = null,
        var releaseYear: String? = null,
        var score: String? = null,
        var member: String? = null,
        var codeYoutube: String? = null,
        var imgPoster: String? = null,
        var favorite: Boolean = false
)