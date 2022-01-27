package com.tochukwu.starwarspractice.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PosterDtoItem(
    val description: String?,
    val gif: String?,
    val id: Int?,
    val name: String?,
    val playtime: String?,
    val plot: String?,
    val poster: String?,
    val release: String?
) : Parcelable