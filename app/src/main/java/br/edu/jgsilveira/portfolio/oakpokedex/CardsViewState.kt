package br.edu.jgsilveira.portfolio.oakpokedex

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Cards

data class CardsViewState(
    var isLoading: Boolean = false,
    var cards: Cards? = null,
    var error: String? = null
)