package com.example.avatarthelastairbender.di

import com.example.avatarthelastairbender.common.Constants
import com.example.avatarthelastairbender.data.AvatarRepositoryImpl
import com.example.avatarthelastairbender.data.remote.AvatarApi
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAvatarApi(): AvatarApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AvatarApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAvatarRepository(api: AvatarApi): AvatarRepository {
        return AvatarRepositoryImpl(api)
    }
}