package com.tochukwu.starwarspractice.data.cache

import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Query
import com.google.common.truth.Truth.assertThat
import com.tochukwu.starwarspractice.data.cache.model.PosterEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class DisneyDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: DisneyDatabase
    private lateinit var dao : DisneyDao

    @Before
    fun setup(){
        hiltRule.inject()
        dao = database.disneyDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun RnsertDisney() = runBlocking{
      //  val disney = PosterEntity(1, "jj", "klkk", "ooll", "mmm", "npn", "oyo")
      //  val disney2 = PosterEntity(3, "bb", "kmkk", "uull", "mmn", "npe", "ouio")
      //  val disney3 = PosterEntity(7, "juu", "kkk", "llbb", "omm", "nxn", "ouo")

      //  val disneyList = listOf(disney, disney2, disney3)
      //  dao.insertDisney(disneyList)
       // val characters : List<PosterEntity> = dao.returnAllPosters()
       // assertThat(disneyList).containsExactly(characters)
    }

    @Test
    fun getPosterById() = runBlocking{

        val disney = PosterEntity(1, "jj", "klkk", "ooll", "mmm", "npn", "oyo")
        val disney2 = PosterEntity(3, "bb", "kmkk", "uull", "mmn", "npe", "ouio")
        val disney3 = PosterEntity(7, "juu", "kkk", "llbb", "omm", "nxn", "ouo")

        val disneyList = listOf(disney, disney2, disney3)
        dao.insertDisney(disneyList)

        val character: PosterEntity =  dao.getPosterById(1)
        assertThat(disney).isEqualTo(character)

    }




}

/**

@Test
fun insertMovie() = runBlockingTest {
val movie = MovieEntity(1, "Rush", "2013", "poster")
dao.insertMovie(movie)

val movies = dao.returnAllMovies().first()

assertThat(movies).contains(movie)
}

@Test
fun insertMovieList() = runBlockingTest {
val movie = MovieEntity(1, "Ford v Ferrari", "2019", "poster1")
val movie2 = MovieEntity(2, "Rush", "2013", "poster2")
val movie3 = MovieEntity(3, "Cars", "2006", "poster3")
val movieList = listOf(movie, movie2, movie3)

dao.insertMovieList(movieList)

val movies: List<MovieEntity> = dao.returnAllMovies().first()

assertThat(movieList).containsExactlyElementsIn(movies)
}

@Test
fun deleteMovie() = runBlockingTest {
val movie = MovieEntity(1, "Rush", "2013", "poster")
dao.insertMovie(movie)
dao.deleteMovie(movie)

val movies = dao.returnAllMovies().first()

assertThat(movies).doesNotContain(movie)
}

@Test
fun deleteMovieList() = runBlockingTest {
val movie = MovieEntity(1, "Ford v Ferrari", "2019", "poster1")
val movie2 = MovieEntity(2, "Rush", "2013", "poster2")
val movie3 = MovieEntity(3, "Cars", "2006", "poster3")
val movieList = listOf(movie, movie2, movie3)

dao.insertMovieList(movieList)
dao.deleteMovieList(movieList)

val movies: List<MovieEntity> = dao.returnAllMovies().first()

assertThat(movieList).doesNotContain(movies)
}


}package com.rradzzio.chooseamovie.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.rradzzio.chooseamovie.data.local.model.MovieEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MoviesDaoTest {

@get:Rule
val hiltRule = HiltAndroidRule(this)

@get:Rule
val instantExecutorRule = InstantTaskExecutorRule()

@Inject
@Named("test_db")
lateinit var database: MovieDatabase

private lateinit var dao: MoviesDao

@Before
fun setup() {
hiltRule.inject()
dao = database.moviesDao()
}

@After
fun teardown() {
database.close()
}

@Test
fun insertMovie() = runBlockingTest {
val movie = MovieEntity(1, "Rush", "2013", "poster")
dao.insertMovie(movie)

val movies = dao.returnAllMovies().first()

assertThat(movies).contains(movie)
}

@Test
fun insertMovieList() = runBlockingTest {
val movie = MovieEntity(1, "Ford v Ferrari", "2019", "poster1")
val movie2 = MovieEntity(2, "Rush", "2013", "poster2")
val movie3 = MovieEntity(3, "Cars", "2006", "poster3")
val movieList = listOf(movie, movie2, movie3)

dao.insertMovieList(movieList)

val movies: List<MovieEntity> = dao.returnAllMovies().first()

assertThat(movieList).containsExactlyElementsIn(movies)
}

@Test
fun deleteMovie() = runBlockingTest {
val movie = MovieEntity(1, "Rush", "2013", "poster")
dao.insertMovie(movie)
dao.deleteMovie(movie)

val movies = dao.returnAllMovies().first()

assertThat(movies).doesNotContain(movie)
}

@Test
fun deleteMovieList() = runBlockingTest {
val movie = MovieEntity(1, "Ford v Ferrari", "2019", "poster1")
val movie2 = MovieEntity(2, "Rush", "2013", "poster2")
val movie3 = MovieEntity(3, "Cars", "2006", "poster3")
val movieList = listOf(movie, movie2, movie3)

dao.insertMovieList(movieList)
dao.deleteMovieList(movieList)

val movies: List<MovieEntity> = dao.returnAllMovies().first()

assertThat(movieList).doesNotContain(movies)
}


}

 **/