package antoniojoseuchoa.com.core.data.repository

import androidx.paging.PagingSource
import antoniojoseuchoa.com.core.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacter(query: String): PagingSource<Int, Character>
}
