package br.edu.jgsilveira.portfolio.oakpokedex.sets

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Sets

sealed class SetsNetworkState {

    object Loading: SetsNetworkState()

    data class Loaded(val data: Sets): SetsNetworkState()

    data class Error(val failure: NetworkResult.Failure): SetsNetworkState()

}