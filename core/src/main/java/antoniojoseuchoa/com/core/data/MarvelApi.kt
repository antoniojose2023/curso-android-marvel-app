package antoniojoseuchoa.com.core.data

import antoniojoseuchoa.com.core.data.response.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {
    @GET("characters")
    suspend fun getCharacters(@QueryMap queries: Map<String, String>): CharacterDataWrapper
}