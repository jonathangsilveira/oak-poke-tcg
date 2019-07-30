package br.edu.jgsilveira.portfolio.oakpokedex.tcg

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult

sealed class NetworkState {

    object Loading: NetworkState()

    data class Loaded(val value: Any?): NetworkState()

    data class Error(val cause: NetworkResult.Failure): NetworkState()

}