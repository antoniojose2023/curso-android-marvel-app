package antoniojoseuchoa.com.core.usecase


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import antoniojoseuchoa.com.core.data.repository.CharacterRepository
import antoniojoseuchoa.com.core.usecase.base.PagingUseCase
import antoniojoseuchoa.com.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val characterRepository: CharacterRepository
    ) : PagingUseCase<GetCharacterUseCase.GetCharacterParams, Character>() {

    override fun createFlowObservable(params: GetCharacterParams): Flow<PagingData<Character>> {
        return Pager(config = params.pagingConfig) {
              characterRepository.getCharacter(params.query)
        }.flow
    }

    data class GetCharacterParams(val query: String, val pagingConfig: PagingConfig)
}
