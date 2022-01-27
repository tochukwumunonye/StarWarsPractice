package com.tochukwu.starwarspractice.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posterTable")
data class PosterEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int? = null,

    @ColumnInfo(name = "name")
    val name:String? = null,

    @ColumnInfo(name = "poster")
    val poster: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "gif")
    val gif: String? = null,


    @ColumnInfo(name = "plot")
    val plot: String? = null,


    @ColumnInfo(name = "release")
    val release: String? = null,

    @ColumnInfo(name = "playtime")
    val playtime: String? = null
) {
}
