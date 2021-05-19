package com.example.moviedb.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_data")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "dataId")
    var dataId: Int,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "genre")
    var genre: String? = null,

    @ColumnInfo(name = "year")
    var releaseYear: String? = null,

    @ColumnInfo(name = "score")
    var score: String? = null,

    @ColumnInfo(name = "member")
    var member: String? = null,

    @ColumnInfo(name = "codeYoutube")
    var codeYoutube: String? = null,

    @ColumnInfo(name = "imgPoster")
    var imgPoster: String? = null,

    @NonNull
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
