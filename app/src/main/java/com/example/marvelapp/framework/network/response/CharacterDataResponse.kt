package com.example.marvelapp.framework.network.response

data class CharacterDataResponse(
    val offset: Int,
    val total: Int,
    val results: List<CharacterResponse>
)
