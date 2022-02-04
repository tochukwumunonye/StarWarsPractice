package com.tochukwu.starwarspractice.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.tochukwu.starwarspractice.presentation.detail.DisneyDetail
import com.tochukwu.starwarspractice.presentation.detail.DisneyViewModel
import com.tochukwu.starwarspractice.repository.FakeRepositoryAndroidTest
import javax.inject.Inject

class TestFragmentFactory @Inject constructor(
    private val disneyAdapter: DisneyAdapter
) :FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            CharacterFragment::class.java.name -> CharacterFragment(disneyAdapter, CharacterViewModel(FakeRepositoryAndroidTest()))
            DisneyDetail::class.java.name -> DisneyDetail(DisneyViewModel(FakeRepositoryAndroidTest()))
            else -> super.instantiate(classLoader, className)
        }
    }
}


/**


override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
return when(className) {
MoviesFragment::class.java.name -> MoviesFragment(
movieListAdapter,
MovieViewModel(FakeMovieRepositoryAndroidTest())
)
AddMovieFragment::class.java.name -> AddMovieFragment(
addMovieListAdapter,
MovieViewModel(FakeMovieRepositoryAndroidTest())
)
else -> super.instantiate(classLoader, className)
}
}

class TestShoppingFragmentFactory @Inject constructor(
private val imageAdapter: ImageAdapter,
private val glide: RequestManager,
private val shoppingItemAdapter: ShoppingItemAdapter
): FragmentFactory() {

override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
return when(className) {
ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
ShoppingFragment::class.java.name -> ShoppingFragment(
shoppingItemAdapter,
ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
)
else -> super.instantiate(classLoader, className)
}
}
}**/