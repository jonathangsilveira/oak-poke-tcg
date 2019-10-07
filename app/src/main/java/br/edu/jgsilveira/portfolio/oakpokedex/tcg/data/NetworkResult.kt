package br.edu.jgsilveira.portfolio.oakpokedex.tcg.data

sealed class NetworkResult<T> {

    data class Success<T>(val value: T?) : NetworkResult<T>()

    sealed class Failure<T>: NetworkResult<T>() {

        data class Response<T>(val body: ErrorResponse? = null): Failure<T>()

        data class Undefined<T>(val cause: Throwable): Failure<T>()

    }

}