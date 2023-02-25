package com.example.marvelapp.framework.network.response
import antoniojoseuchoa.com.core.domain.model.Character

data class CharacterResponse(
    val id: Int,
    var name: String,
    var thumbnail: ThumbnailResponse
)


fun CharacterResponse.toCharacter() = Character(
    name = name,
    image = "${this.thumbnail.path}.${this.thumbnail.extension}".replace("http", "https")
)
