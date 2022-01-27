package com.tochukwu.starwarspractice.data.cache

import androidx.room.*
import com.tochukwu.starwarspractice.data.cache.model.PosterEntity
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DisneyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisney(infos: List<PosterEntity>)


    @Query("SELECT * FROM posterTable")
    fun returnAllPosters(): List<PosterEntity>



    @Query("DELETE FROM posterTable")
    suspend fun deleteCache()

    @Query("SELECT * FROM posterTable WHERE id = :id")
    suspend fun getPosterById(id: Int?): PosterEntity

}


/**
 *
 *  @Query("SELECT * FROM article_table WHERE id = :id")
suspend fun getNewsById(id: String): Article

interface WordInfoDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertWordInfos(infos: List<WordInfoEntity>)


@Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
suspend fun deleteWordInfos(words: List<String>)

@Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
suspend fun getWordInfos(word: String): List<WordInfoEntity>
}

**/