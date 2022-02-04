package com.tochukwu.starwarspractice.presentation

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.tochukwu.starwarspractice.FakePosterAndroidTest.posters
import com.tochukwu.starwarspractice.R
import com.tochukwu.starwarspractice.launchFragmentInHiltsContainer
import com.tochukwu.starwarspractice.repository.FakeRepositoryAndroidTest

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class CharacterFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var testFragmentFactory: TestFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun test_isRecyclerViewVisible_whenCharacterFragmentLaunches(){
        launchFragmentInHiltsContainer<CharacterFragment>(
            fragmentFactory = testFragmentFactory){

            disneyAdapter.submitList(posters)
        }
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }






    @Test
    fun recycler_view_click_navigates(){
        val navController = mock(NavController::class.java)
        launchFragmentInHiltsContainer<CharacterFragment>(
            fragmentFactory = testFragmentFactory
        ){
            disneyAdapter.submitList(posters)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<DisneyAdapter.PosterViewHolder>(
            0, click()
        ))
        verify(navController).navigate(CharacterFragmentDirections.actionCharacterFragmentToDisneyDetail(posters.get(0)))
    }
}

/**

verify(navController).navigate(
MoviesFragmentDirections.actionMoviesFragmentToAddMovieFragment()
)
}

@Test
fun clickImage_popBackStackAndSetImageUrl() {
val navController = mock(NavController::class.java)
val imageUrl = "TEST"
val testViewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
launchFragmentInHiltsContainer<ImagePickFragment> (fragmentFactory = fragmentFactory) {
Navigation.setViewNavController(requireView(), navController)
imageAdapter.images = listOf(imageUrl)
viewModel = testViewModel
}

onView(withId(R.id.rvImages)).perform(
RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(
0,
click()
)

)
verify(navController).popBackStack()
assertThat(testViewModel.curImageUrl.getOrAwaitValue()).isEqualTo(imageUrl)
}


@Test
fun swipeMovieItem_deleteMovieInDB() {
var testMovieViewModel: MovieViewModel? = null
launchFragmentInHiltContainer<MoviesFragment>(
fragmentFactory = testFragmentFactory
) {
testMovieViewModel = viewModel
testMovieViewModel?.insertMovie(FakeMovieDataAndroidTest.movie)
}

onView(withId(R.id.rvMovieItems)).perform(
RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
0, swipeLeft()
)
)

assertThat(testMovieViewModel?.moviesFromDb?.value).doesNotContain(FakeMovieDataAndroidTest.movie)

onView(ViewMatchers.withText(Constants.DELETED_MOVIE_FROM_DB))
.inRoot(ToastMatcher())
.check(matches(isDisplayed()))
}

}








































@Test
fun clickAddMovieButton_navigateToAddMovieFragment() {
val navController = mock(NavController::class.java)
launchFragmentInHiltContainer<MoviesFragment>(
fragmentFactory = testFragmentFactory
) {
Navigation.setViewNavController(requireView(), navController)
}

onView(withId(R.id.fabAddMovie)).perform(click())

verify(navController).navigate(
MoviesFragmentDirections.actionMoviesFragmentToAddMovieFragment()
)
}



@Test
fun swipeMovieItem_deleteMovieInDB() {
var testMovieViewModel: MovieViewModel? = null
launchFragmentInHiltContainer<MoviesFragment>(
fragmentFactory = testFragmentFactory
) {
testMovieViewModel = viewModel
testMovieViewModel?.insertMovie(FakeMovieDataAndroidTest.movie)
}

onView(withId(R.id.rvMovieItems)).perform(
RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
0, swipeLeft()
)
)

assertThat(testMovieViewModel?.moviesFromDb?.value).doesNotContain(FakeMovieDataAndroidTest.movie)

onView(ViewMatchers.withText(Constants.DELETED_MOVIE_FROM_DB))
.inRoot(ToastMatcher())
.check(matches(isDisplayed()))
}

}

 **/