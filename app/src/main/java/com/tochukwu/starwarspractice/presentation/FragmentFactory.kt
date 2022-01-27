package com.tochukwu.starwarspractice.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.tochukwu.starwarspractice.presentation.detail.DisneyDetail
import javax.inject.Inject

class FragmentFactory  @Inject constructor(
    private val disneyAdapter: DisneyAdapter
) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            CharacterFragment::class.java.name -> CharacterFragment(disneyAdapter)
            DisneyDetail::class.java.name -> DisneyDetail()
            else -> super.instantiate(classLoader, className)
        }

    }
}



/**

class FragmentFactory @Inject constructor(
private val movieListAdapter: MovieListAdapter,
private val addMovieListAdapter: AddMovieListAdapter
) : FragmentFactory() {

override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
return when(className) {
MoviesFragment::class.java.name -> MoviesFragment(movieListAdapter)
AddMovieFragment::class.java.name -> AddMovieFragment(addMovieListAdapter)
else -> super.instantiate(classLoader, className)
}
}
} **/