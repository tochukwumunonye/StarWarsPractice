package com.tochukwu.starwarspractice.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tochukwu.starwarspractice.MainCoroutinesRule
import com.tochukwu.starwarspractice.domain.FakeRepositoryTest
import com.tochukwu.starwarspractice.presentation.detail.DisneyViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import com.google.common.truth.Truth.assertThat
import com.tochukwu.starwarspractice.getOrAwaitValueTest
import com.tochukwu.starwarspractice.util.Status
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTest {



    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutinesRule()


    private lateinit var viewModel: CharacterViewModel
    private lateinit var fakeRepo: FakeRepositoryTest

    @Before
    fun setup(){
       fakeRepo = FakeRepositoryTest()
       viewModel = CharacterViewModel(fakeRepo)
    }



    @Test
    fun getDisneyList() {

        fakeRepo.setShouldReturnNetworkError(true)
       // viewModel = CharacterViewModel(fakeRepo)
        viewModel.getDisney()



        val value = viewModel.disneyChannel.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }
    /**

    @get:Rule
    var mainCoroutineRule = MainCoroutinesRule()


    private lateinit var viewModel: CharacterViewModel

    @Before
    //  fun setup(){
    //      viewModel = CharacterViewModel(FakeRepositoryTest())
    //  }



    @Test
    fun getDisneyList() {
    val fakeRepo = FakeRepositoryTest()
    fakeRepo.setShouldReturnNetworkError(true)
    viewModel = CharacterViewModel(fakeRepo)
    viewModel.getDisney()



    val value = viewModel.disneyChannel.getOrAwaitValueTest()
    assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

     */
}