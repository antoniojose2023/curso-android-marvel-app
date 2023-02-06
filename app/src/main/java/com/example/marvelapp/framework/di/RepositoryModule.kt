package com.example.marvelapp.framework.di

import antoniojoseuchoa.com.core.data.repository.CharacterRemoteDataSource
import antoniojoseuchoa.com.core.data.repository.CharacterRepository
import com.example.marvelapp.framework.CharacterRepositoryImpl
import com.example.marvelapp.framework.network.response.CharacterDataWrapper
import com.example.marvelapp.framework.remote.RetrofitCharacterRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun binCharacterRepositoryImpl(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    fun binCharaterRemoteDataSource(retrofitCharacterRemoteDataSource: RetrofitCharacterRemoteDataSource): CharacterRemoteDataSource<CharacterDataWrapper>
}