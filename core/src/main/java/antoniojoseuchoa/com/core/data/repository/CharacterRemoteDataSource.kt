package antoniojoseuchoa.com.core.data.repository

interface CharacterRemoteDataSource<T> {
   suspend fun fetchCharacter(query: Map<String, String>): T
}
