package antoniojoseuchoa.com.core.data.network

import antoniojoseuchoa.com.core.data.network.response.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {
    @GET("characters")
    suspend fun getCharecters(@QueryMap queries: Map<String, String>): CharacterDataWrapper
}
