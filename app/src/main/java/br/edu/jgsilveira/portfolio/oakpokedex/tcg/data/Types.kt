package br.edu.jgsilveira.portfolio.oakpokedex.tcg.data

import com.google.gson.annotations.SerializedName

data class Types(@SerializedName("types") val values: List<String> = listOf())