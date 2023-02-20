package com.example.marvelapp.framework.remote

import antoniojoseuchoa.com.core.data.repository.CharacterRemoteDataSource
import com.example.marvelapp.framework.network.MarvelApi
import com.example.marvelapp.framework.network.response.CharacterDataWrapper
import javax.inject.Inject

class RetrofitCharacterRemoteDataSource @Inject constructor(private val marvelApi: MarvelApi) :
    CharacterRemoteDataSource<CharacterDataWrapper> {
    override suspend fun fetchCharacter(queries: Map<String, String>): CharacterDataWrapper {
         return marvelApi.getCharacters(queries)
    }
}
