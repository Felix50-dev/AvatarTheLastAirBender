package com.example.avatarthelastairbender.di

import com.example.avatarthelastairbender.data.AvatarRepositoryImpl
import com.example.avatarthelastairbender.data.remote.AvatarApi
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAvatarApi(): AvatarApi {
        return AvatarApi.create()
    }

    @Provides
    @Singleton
    fun provideAvatarRepository(api: AvatarApi): AvatarRepository {
        return AvatarRepositoryImpl(api)
    }
}