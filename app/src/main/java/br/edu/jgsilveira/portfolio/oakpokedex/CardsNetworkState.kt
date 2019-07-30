package br.edu.jgsilveira.portfolio.oakpokedex

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Cards
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult

sealed class CardsNetworkState {

    object Loading: CardsNetworkState()

    data class Loaded(val value: Cards): CardsNetworkState()

    data class Error(val failure: NetworkResult.Failure): CardsNetworkState()

}