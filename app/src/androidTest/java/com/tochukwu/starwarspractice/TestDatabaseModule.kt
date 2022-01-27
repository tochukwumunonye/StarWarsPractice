package com.tochukwu.starwarspractice

import android.content.Context
import androidx.room.Room
import com.tochukwu.starwarspractice.data.cache.DisneyDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {

    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(context,
        DisneyDatabase::class.java ).allowMainThreadQueries().build()


}


