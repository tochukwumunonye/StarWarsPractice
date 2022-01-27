package com.tochukwu.starwarspractice.presentation.detail

import androidx.lifecycle.*
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import com.tochukwu.starwarspractice.domain.MainRepository
import com.tochukwu.starwarspractice.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DisneyViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel(){


    private val _articleId = MutableLiveData<Int>()

    val articleLiveData: LiveData<PosterDtoItem> = _articleId.switchMap{ id->
        repository.getPosterById(id).asLiveData()
    }

    fun fetchArticle(id: Int?){
        _articleId.value = id!!
    }

}

/**



private val _articleId = MutableLiveData<String>()
private val _shareArticleEvent = MutableLiveData<SingleEvent<String>>()
private val _openWebsiteEvent = MutableLiveData<SingleEvent<String>>()

val openWebsiteEvent: LiveData<SingleEvent<String>>
get() = _openWebsiteEvent


val shareArticleEvent: LiveData<SingleEvent<String>>
get() = _shareArticleEvent


val articleLiveData: LiveData<Article> = _articleId.switchMap{id->
repository.getArticle(id).asLiveData()
}

fun fetchArticle(id: String){
_articleId.value = id
}

 **/