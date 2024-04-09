package com.drabatx.rickandmortyepisode.di

import com.drabatx.rickandmortyepisode.data.datasource.RemoteDataSource
import com.drabatx.rickandmortyepisode.data.network.CharactersApiService
import com.drabatx.rickandmortyepisode.domain.repository.AllCharactersRepository
import com.drabatx.rickandmortyepisode.domain.repository.AllEpisodesRepository
import com.drabatx.rickandmortyepisode.domain.repository.impl.AllCharacterRepositoryImpl
import com.drabatx.rickandmortyepisode.domain.repository.impl.AllEpisodesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("accept", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            })
            .build()
        return Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesCharacterApiService(retrofit: Retrofit): CharactersApiService {
        return retrofit.create(CharactersApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(charactersApiService: CharactersApiService): RemoteDataSource {
        return RemoteDataSource(charactersApiService)
    }

    @Provides
    @Singleton
    fun provideAllCharactersRepository(remoteDataSource: RemoteDataSource): AllCharactersRepository {
        return AllCharacterRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideAllEpisodesRepository(remoteDataSource: RemoteDataSource): AllEpisodesRepository{
        return AllEpisodesRepositoryImpl(remoteDataSource)
    }
}
