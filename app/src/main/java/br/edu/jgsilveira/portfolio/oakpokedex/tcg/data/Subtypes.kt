package br.edu.jgsilveira.portfolio.oakpokedex.tcg.data


import com.google.gson.annotations.SerializedName

data class Subtypes(@SerializedName(value = "values") val values: List<String> = listOf())