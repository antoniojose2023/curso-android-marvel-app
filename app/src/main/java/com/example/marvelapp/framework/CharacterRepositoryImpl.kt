package com.example.marvelapp.framework


import androidx.paging.PagingSource
import antoniojoseuchoa.com.core.data.repository.CharacterRemoteDataSource
import antoniojoseuchoa.com.core.data.repository.CharacterRepository
import antoniojoseuchoa.com.core.domain.model.Character
import com.example.marvelapp.framework.network.response.CharacterDataWrapper
import com.example.marvelapp.framework.paging.CharacterPagingSource
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource<CharacterDataWrapper>
 ): CharacterRepository {
    override suspend fun getCharacter(query: String): PagingSource<Int, Character> {
        return CharacterPagingSource(remoteDataSource, query)
    }
 }
