package com.example.moviedb.core.utils

import com.example.moviedb.core.data.source.local.entity.DataEntity
import com.example.moviedb.core.domain.model.DataModel

object DataMapper {
    fun mapDataEntitiesToDomain(input: List<DataEntity>): List<DataModel> =
        input.map {
            DataModel(
                it.id,
                it.dataId,
                it.type,
                it.title,
                it.description,
                it.genre,
                it.releaseYear,
                it.score,
                it.member,
                it.codeYoutube,
                it.imgPoster,
                it.favorite
            )
        }

    fun mapDomainToDataEntities(input: DataModel) : DataEntity =
        DataEntity(
            input.id,
            input.dataId,
            input.type,
            input.title,
            input.description,
            input.genre,
            input.releaseYear,
            input.score,
            input.member,
            input.codeYoutube,
            input.imgPoster,
            input.favorite
        )

    fun mapDetailData(input: DataEntity): DataModel =
        DataModel(
            input.id,
            input.dataId,
            input.type,
            input.title,
            input.description,
            input.genre,
            input.releaseYear,
            input.score,
            input.member,
            input.codeYoutube,
            input.imgPoster,
            input.favorite
        )
}