package br.edu.jgsilveira.portfolio.oakpokedex.sets

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Sets

class SetsViewState private constructor(
    val isLoading: Boolean = false,
    val result: Sets? = null,
    var error: String? = null
) {

    companion object {

        fun loading() = SetsViewState(isLoading = true)

        fun loaded(data: Sets?) = SetsViewState(result = data)

        fun error(message: String) = SetsViewState(error = message)

    }

}