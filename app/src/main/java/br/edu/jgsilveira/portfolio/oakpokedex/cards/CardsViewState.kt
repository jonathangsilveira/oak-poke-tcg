package br.edu.jgsilveira.portfolio.oakpokedex.cards

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Cards

class CardsViewState private constructor(
    var isLoading: Boolean = false,
    var cards: Cards? = null,
    var error: String? = null
) {

    companion object {

        fun loading() = CardsViewState(isLoading = true)

        fun loaded(data: Cards?) = CardsViewState(cards = data)

        fun error(message: String) = CardsViewState(error = message)

    }

}