package br.edu.jgsilveira.portfolio.oakpokedex.tcg.webservice

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Cards
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Sets
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Subtypes
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Types
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface WebService {

    @GET(value = "cards")
    fun cards(@QueryMap query: Map<String, String>): Call<Cards>

    @GET(value = "sets")
    fun sets(@QueryMap query: Map<String, String>): Call<Sets>

    @GET(value = "cards")
    fun setCards(@Query(value = "setCode") setCode: String): Call<Cards>

    @GET(value = "types")
    fun types(): Call<Types>

    @GET(value = "subtypes")
    fun subtypes(): Call<Subtypes>

}