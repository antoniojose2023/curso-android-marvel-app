package antoniojoseuchoa.com.core.usecase.base

sealed class ResultStatus<out T> {
    object Loader : ResultStatus<Nothing>()
    class Sucess<out T>(val data: T) : ResultStatus<T>()
    class Erro(val throwable: Throwable) : ResultStatus<Nothing>()

    override fun toString(): String {
       return when (this) {
           is Erro -> "Erro : [$throwable]"
           Loader -> "Loader"
           is Sucess -> "Dados : [$data]"
       }
    }
}


