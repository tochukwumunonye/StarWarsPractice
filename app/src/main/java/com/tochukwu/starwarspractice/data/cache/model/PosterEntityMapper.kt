package com.tochukwu.starwarspractice.data.cache.model

import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import com.tochukwu.starwarspractice.util.DomainMapper

class PosterEntityMapper : DomainMapper<PosterEntity, PosterDtoItem> {


    override fun mapToDomainModel(model: PosterEntity): PosterDtoItem {
        return PosterDtoItem(
            description = model.description,
            gif = model.gif,
            id =  model.id,
            name = model.name,
            playtime = model.playtime,
            plot = model.plot,
            poster = model.poster,
            release = model.release
        )
    }

    override fun mapFromDomainModel(domainModel: PosterDtoItem): PosterEntity {
        return PosterEntity(
            description = domainModel.description,
            gif = domainModel.gif,
            id = domainModel.id,
            name = domainModel.name,
            playtime = domainModel.playtime,
            plot = domainModel.plot,
            poster = domainModel.poster,
            release = domainModel.release
        )
    }

    fun fromEntityListDomain(posterEntityList: List<PosterEntity>): List<PosterDtoItem>{
        return posterEntityList.map{mapToDomainModel(it)}
    }

    fun fromDomainListToEntity(posterList : List<PosterDtoItem>) : List<PosterEntity>{
        return posterList.map{mapFromDomainModel(it)}
    }

}
