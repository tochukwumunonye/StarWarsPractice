package com.tochukwu.starwarspractice.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tochukwu.starwarspractice.MainCoroutinesRule
import com.tochukwu.starwarspractice.domain.FakeRepositoryTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.tochukwu.starwarspractice.util.Status
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor

import com.google.common.truth.Truth.assertThat
import com.tochukwu.starwarspractice.getOrAwaitValueTest
import org.mockito.Captor

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DisneyViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutinesRule()

    private lateinit var viewModel: DisneyViewModel

    @Mock
    private lateinit var observerState: Observer<Status>


    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<Status>

    @Before
    fun setup(){
        viewModel = DisneyViewModel(FakeRepositoryTest())
    }


    @Test
    fun `get poster detail from database `(){

        viewModel.fetchArticle(8)
        val value = viewModel.articleLiveData.getOrAwaitValueTest()
        assertThat(value).isNotNull()
    }


    //assertThat(viewModel.disneyChannel.value?.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

}


/**
 *
 *
 *   @Test
fun `get data should fail`(){

var fakeRepo = FakeCoinRepository()
fakeRepo.setShouldReturnNetworkError(true)

viewModel.getCoins()


assertThat(viewModel.coinz.value?.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

// assertThat(viewModel.coinz.getOrAwaitValueTest()).isEqualTo(null)
}
 *
 *
@Before
fun setup() {
viewModel = MovieViewModel(FakeMovieRepository())
}

@Test
fun `get movies from network with empty query returns error`() {
viewModel.getMovies("")

val value = viewModel.movies.getOrAwaitValueTest()

assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
}

@Test
fun `get movies from network with too long query returns error`() {
val string = buildString {
for(i in 1..Constants.MAX_MOVIE_TITLE_LENGTH + 1) {
append("a")
}
}
viewModel.getMovies(string)

val value = viewModel.movies.getOrAwaitValueTest()

assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
}

@Test
fun `get movies before network request returns loading then success`() = runBlocking{
val expectedLoadingState = Status.LOADING
val expectedSuccessState = Status.SUCCESS

viewModel.movies.observeForever {
argumentCaptor.apply {
verify(observerState).onChanged(argumentCaptor.capture())
val (loadingState, successState) = allValues
//expect first emitted value to be Resource's State (Status.Loading)
assertThat(loadingState).isEqualTo(expectedLoadingState)
//expect last emitted value to be Resource's State (Status.Success)
assertThat(successState).isEqualTo(expectedSuccessState)
}
}
}

@Test
fun `get movies from network with valid query returns success`() {
viewModel.getMovies("query")

val value = viewModel.movies.getOrAwaitValueTest()

assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
}

@Test
fun `insert movie returns success`() {
viewModel.insertMovie(movie)
viewModel.returnAllMoviesFromDb()

val value = viewModel.moviesFromDb.getOrAwaitValueTest()

assertThat(value).contains(movie)
}

@Test
fun `insert movie list returns success`() {
viewModel.insertMovieList(movies)
viewModel.returnAllMoviesFromDb()

val value = viewModel.moviesFromDb.getOrAwaitValueTest()

assertThat(value).containsExactlyElementsIn(movies)
}

@Test
fun `delete movie returns success`() {
viewModel.insertMovie(movie)
viewModel.deleteMovie(movie)
viewModel.returnAllMoviesFromDb()

val value = viewModel.moviesFromDb.getOrAwaitValueTest()

assertThat(value).doesNotContain(movie)
}

@Test
fun `delete movie list returns success`() {
viewModel.insertMovieList(movies)
viewModel.deleteMovieList(movies)
viewModel.returnAllMoviesFromDb()

val value = viewModel.moviesFromDb.getOrAwaitValueTest()

assertThat(value).doesNotContain(movies)
}

} **/