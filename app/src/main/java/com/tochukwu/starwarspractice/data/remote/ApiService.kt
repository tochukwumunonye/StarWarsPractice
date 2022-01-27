package com.tochukwu.starwarspractice.data.remote

import com.tochukwu.starwarspractice.data.remote.model.PosterDto
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    //suspend fun fetchDisneyPosterList(): Response<PosterDto>


    @GET("DisneyPosters2.json")
    suspend fun fetchDisneyPosterList(): Response<List<PosterDtoItem>>
}


