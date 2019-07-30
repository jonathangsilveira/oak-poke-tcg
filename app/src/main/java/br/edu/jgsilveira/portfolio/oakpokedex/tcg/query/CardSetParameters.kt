package br.edu.jgsilveira.portfolio.oakpokedex.tcg.query

data class CardSetParameters(
    var name: String? = null,
    var series: String? = null,
    var totalCards: String? = null,
    var standardLegal: Boolean? = null,
    var expandedLegal: Boolean? = null,
    var pageSize: Int? = null
)