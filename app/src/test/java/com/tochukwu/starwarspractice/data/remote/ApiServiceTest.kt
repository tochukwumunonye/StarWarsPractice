package com.tochukwu.starwarspractice.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tochukwu.starwarspractice.MainCoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ApiServiceTest : ApiAbstract<ApiService>() {
    @get: Rule
    val coroutineRule = MainCoroutinesRule()

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ApiService

    @Before
    fun initService(){
        service = createService(ApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun getDisneyFromNetwork() = runBlocking{
        enqueueResponse("/DisneyResponse.json")
        val response = (service.fetchDisneyPosterList())
        val responseBody = response.body()
        mockWebServer.takeRequest()

        val loaded = responseBody?.get(0)
        MatcherAssert.assertThat(loaded?.name, CoreMatchers. `is`("Frozen II"))
        MatcherAssert.assertThat(loaded?.release, CoreMatchers. `is` ("2019"))

        MatcherAssert.assertThat(loaded?.description, CoreMatchers. `is` ("Frozen II, " +
                "also known as Frozen 2, is a 2019 American 3D computer-animated musical fantasy film produced by " +
                "Walt Disney Animation Studios. The 58th animated film produced by the studio, it is the sequel to the " +
                "2013 film Frozen and features the return of directors Chris Buck and Jennifer Lee, producer Peter " +
                "Del Vecho, songwriters Kristen Anderson-Lopez and Robert Lopez, and composer Christophe Beck. Lee also" +
                " returns as screenwriter, penning the screenplay from a story by her, Buck, Marc E. Smith, Anderson-Lopez, " +
                "and Lopez,[2] while Byron Howard executive-produced the film.[a][1] Veteran voice cast Kristen Bell," +
                " Idina Menzel, Josh Gad, Jonathan Groff, and Ciarán Hinds return as their previous characters, and" +
                " they are joined by newcomers Sterling K. " +
                "Brown, Evan Rachel Wood, Alfred Molina, Martha Plimpton, Jason Ritter, Rachel Matthews, and Jeremy Sisto."))


    }

}




