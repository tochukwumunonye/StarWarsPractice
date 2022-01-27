package com.tochukwu.starwarspractice.domain

import com.tochukwu.starwarspractice.data.remote.model.PosterDto
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import com.tochukwu.starwarspractice.util.Resource
import kotlinx.coroutines.flow.Flow


interface MainRepository {

    fun getPoster() : Flow<Resource<List<PosterDtoItem>>>

    fun getPosterById(id: Int?) : Flow<PosterDtoItem>
}
/**

fun getMovies(
movieSearchQuery: String
): Flow<Resource<List<Movie>>>

suspend fun insertMovieList(movieList: List<Movie>)

suspend fun insertMovie(movie: Movie)

suspend fun deleteMovie(movie: Movie)

fun returnAllMovies(): Flow<List<Movie>>


suspend fun deleteMovieList(movieList: List<Movie>) **/