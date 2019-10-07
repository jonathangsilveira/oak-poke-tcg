package br.edu.jgsilveira.portfolio.oakpokedex.tcg

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult

sealed class NetworkState<T> {

    class Loading<T>: NetworkState<T>()

    data class Loaded<T>(val value: T?): NetworkState<T>()

    data class Error<T>(val cause: NetworkResult.Failure<T>): NetworkState<T>()

}