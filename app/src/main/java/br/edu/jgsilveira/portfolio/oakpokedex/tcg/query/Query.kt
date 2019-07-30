package br.edu.jgsilveira.portfolio.oakpokedex.tcg.query

interface Query<T> {

    fun <T> all(): List<T>

}