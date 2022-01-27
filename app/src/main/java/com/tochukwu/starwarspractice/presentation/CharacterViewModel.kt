package com.tochukwu.starwarspractice.presentation

import androidx.lifecycle.*
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import com.tochukwu.starwarspractice.domain.MainRepository
import com.tochukwu.starwarspractice.util.Event
import com.tochukwu.starwarspractice.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel  @Inject constructor(
    private val characterRepo : MainRepository
): ViewModel(){

    private val _disneyChannel = MutableLiveData<Event<Resource<List<PosterDtoItem>>>>()
    val disneyChannel: LiveData<Event<Resource<List<PosterDtoItem>>>> get() = _disneyChannel

    fun getDisney() = viewModelScope.launch{

        characterRepo.getPoster().collectLatest{
            _disneyChannel.postValue(Event(it))
        }
    }

}


/**
 *
 * = viewModelScope.launch {
movieRepository.getMovies(searchQuery).collectLatest{
_movies.postValue(Event(it))
}
}

private var currentNews: Flow<PagingData<Article>>? = null

fun loadNews(): Flow<PagingData<Article>>{

val category = _categoryLiveData.value!!
val language = _languageLiveData.value!!

val lastResult = currentNews
if(lastResult != null && !shouldRefresh(language, category)) return lastResult

val newNews = repository.fetchArticles(language, category).cachedIn(viewModelScope)
currentNews = newNews


//Save new filters after checks are made to establish, if the news should be refreshed.
saveCategoryFiltering(category)
saveLanguageFiltering(language)
savedCategoryLocalize()
return newNews

@HiltViewModel
class CharactersViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
ViewModel() {

fun getCharacters(searchString: String): Flow<PagingData<Character>> {
return charactersRepository.getCharacters(searchString).cachedIn(viewModelScope)
}
}





private val _characters = MutableLiveData<Event<Resource<List<Characters>>>>()
val characters: LiveData<Event<Resource<List<Characters>>>> get() = _characters


fun getCharacters(){
_characters.postValue(Event(Resource.loading(null)))
getFromNetwork()

}

private fun getFromNetwork() = viewModelScope.launch {

characterRepo.getCharacters().collectLatest {
_characters.postValue(Event(it))
}
}**/