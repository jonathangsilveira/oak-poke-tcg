package br.edu.jgsilveira.portfolio.oakpokedex.tcg.data


data class Sets(
    val sets: List<Set> = listOf()
) {
    data class Set(
        val code: String,
        val name: String,
        val series: String,
        val totalCards: Int = 0,
        val symbolUrl: String,
        val logoUrl: String
    )
}