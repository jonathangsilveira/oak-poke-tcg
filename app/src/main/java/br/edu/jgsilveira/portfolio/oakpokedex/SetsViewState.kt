package br.edu.jgsilveira.portfolio.oakpokedex

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Sets

data class SetsViewState(
    val isLoading: Boolean = false,
    val result: Sets? = null,
    var error: String? = null
)