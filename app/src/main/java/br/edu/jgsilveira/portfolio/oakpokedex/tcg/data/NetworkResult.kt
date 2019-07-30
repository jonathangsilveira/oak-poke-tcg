package br.edu.jgsilveira.portfolio.oakpokedex.tcg.data

sealed class NetworkResult<out T> {

    data class Success<out T>(val value: T?) : NetworkResult<T>()

    sealed class Failure: NetworkResult<Nothing>() {

        data class Response(val body: ErrorResponse? = null): Failure()

        data class Undefined(val cause: Throwable): Failure()

    }

}