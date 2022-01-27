package com.tochukwu.starwarspractice.data.remote

import com.tochukwu.starwarspractice.data.cache.DisneyDao
import com.tochukwu.starwarspractice.data.cache.model.PosterEntityMapper
import com.tochukwu.starwarspractice.data.remote.model.PosterDto
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import com.tochukwu.starwarspractice.domain.MainRepository
import com.tochukwu.starwarspractice.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dao: DisneyDao,
    private val  api: ApiService,
    private val map: PosterEntityMapper
) : MainRepository{

    override fun getPoster(): Flow<Resource<List<PosterDtoItem>>> = flow{

           emit(Resource.loading(null))
        val cacheResponse = dao.returnAllPosters().map{
            map.mapToDomainModel(it)
        }

        emit(Resource.loading(data =  cacheResponse))

        try{
            val response = api.fetchDisneyPosterList()

            dao.deleteCache()
            dao.insertDisney(response.body()!!.let { map.fromDomainListToEntity(it) })


        } catch(e: HttpException){
            emit(Resource.error("Couldn't reach server", cacheResponse))
        } catch(e : IOException){
            emit(Resource.error("OOps, something went wrong", cacheResponse))
        }

        val newResponse = dao.returnAllPosters().map{ map.mapToDomainModel(it) }
        emit(Resource.success(newResponse))
    }


    override fun getPosterById(id: Int?): Flow<PosterDtoItem> = flow{
       val post = map.mapToDomainModel(dao.getPosterById(id))
        emit(post)
       }

}


/**
 *
fun getArticle(id: String) = flow{
val article = database.articleDao().getNewsById(id)
emit(article)
}.flowOn(Dispatchers.Default)


try{

val response = api.getCharacters()
if(response.isSuccessful){
response.body()?.let{
val characterres = it.results?.let{res->
mapper.fromDtoListToDomain(res)
}
emit(Resource.success(characterres))
}
}else{
emit(Resource.error("An unknown error occurred", null))
}

} catch(e : Exception){

Timber.e("exception: ${e.message}")
emit(Resource.error("Couldn't reach the server. Check internet connection", null))



class MainRepositoryImpl @Inject constructor(
private val  api: ApiService
) : MainRepository{

override fun getPoster(): Flow<Resource<List<PosterDtoItem>>> = flow{

try{
val response = api.fetchDisneyPosterList()
if(response.isSuccessful){
val res = response.body()
emit(Resource.success(res))
} else{
emit(Resource.error("An unknown error occurred", null))
}

} catch(e: Exception){
emit(Resource.error("Couldnt reach server", null))
}
}

} **/