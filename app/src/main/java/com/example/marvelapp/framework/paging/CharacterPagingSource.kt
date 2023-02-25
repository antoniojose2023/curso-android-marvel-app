package com.example.marvelapp.framework.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import antoniojoseuchoa.com.core.data.repository.CharacterRemoteDataSource
import antoniojoseuchoa.com.core.domain.model.Character
import com.example.marvelapp.framework.network.response.CharacterDataWrapper
import com.example.marvelapp.framework.network.response.toCharacter

class CharacterPagingSource(
    private val remoteDataSource: CharacterRemoteDataSource<CharacterDataWrapper>,
    private val query: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        try {
            val offset = params.key ?: 0

            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            if (query.isNotEmpty()) {
                 queries["nameStartsWith"] = query
            }

           val response = remoteDataSource.fetchCharacter(queries)
           val responseOffSet = response.data.offset
           val responseTotal = response.data.total

           return LoadResult.Page(
               data = response.data.results.map { it.toCharacter() },
               prevKey = null,
               nextKey = if (responseOffSet < responseTotal) responseOffSet + LIMIT else null
           )
        } catch (ex: java.lang.Exception) {
            return LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                 val anchorPage = state.closestPageToPosition(anchorPosition)
                 anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
            }
    }

    companion object {
        const val LIMIT = 20
    }
}
