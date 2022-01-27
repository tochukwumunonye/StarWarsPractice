package com.tochukwu.starwarspractice.di

import android.app.Application
import androidx.room.Room
import com.tochukwu.starwarspractice.data.cache.DisneyDao
import com.tochukwu.starwarspractice.data.cache.DisneyDatabase
import com.tochukwu.starwarspractice.data.cache.model.PosterEntityMapper
import com.tochukwu.starwarspractice.data.remote.ApiService
import com.tochukwu.starwarspractice.data.remote.MainRepositoryImpl
import com.tochukwu.starwarspractice.domain.MainRepository
import com.tochukwu.starwarspractice.util.Constants.DISNEY_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun providesBaseUrl(): String {
        return "https://gist.githubusercontent.com/skydoves/176c209dbce4a53c0ff9589e07255f30/raw/6489d9712702e093c4df71500fb822f0d408ef75/"
    }

    @Singleton
    @Provides
    fun provideMovieDtoMapper(): PosterEntityMapper = PosterEntityMapper()

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)

        return retrofit.build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDisneyDao(db: DisneyDatabase) = db.disneyDao()

    @Singleton
    @Provides
    fun provideDisneyDatabase(app : Application) :DisneyDatabase{
        return Room.databaseBuilder(app, DisneyDatabase::class.java, DISNEY_DATABASE_NAME).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(
        dao: DisneyDao,
        api: ApiService,
        map: PosterEntityMapper
    ) = MainRepositoryImpl(
        dao = dao,
        api = api,
        map = map
    ) as MainRepository

}

/**
@Provides
@Singleton
fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
return Room.databaseBuilder(
app, WordInfoDatabase::class.java, "word_db"
).addTypeConverter(Converters(GsonParser(Gson())))
.build()
}



@Provides
@Singleton
fun provideWordInfoRepository(
db: WordInfoDatabase,
api: DictionaryApi
): WordInfoRepository{
return wordInfoRepositoryImpl(api, db.dao)
}



@Provides
@Singleton
fun provideDictionaryApi(): DictionaryApi {
return Retrofit.Builder()
.baseUrl(DictionaryApi.BASE_URL)
.addConverterFactory(GsonConverterFactory.create())
.build()
.create(DictionaryApi::class.java)
}**/