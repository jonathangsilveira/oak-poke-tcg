package br.edu.jgsilveira.portfolio.oakpokedex.tcg

import br.edu.jgsilveira.portfolio.oakpokedex.tcg.RetrofitHelper.service
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.ErrorResponse
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.webservice.WebService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class Endpoint {

    private val webservice: WebService by service()

    private fun <T> safeApiCall(call: () -> Call<T>): NetworkResult<T> {
        return try {
            val response = call().execute()
            if (response.isSuccessful)
                NetworkResult.Success(response.body())
            else
                NetworkResult.Failure.Response(errorResponse(response))
        } catch (e: Exception) {
            NetworkResult.Failure.Undefined(cause = e)
        }
    }

    private fun <T> errorResponse(response: Response<T>): ErrorResponse? {
        return if (response.errorBody() == null)
            null
        else
            Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
    }

    fun types() = safeApiCall {
        webservice.types()
    }

    fun subtypes() = safeApiCall {
        webservice.subtypes()
    }

    fun sets(query: Map<String, String>) = safeApiCall {
        webservice.sets(query)
    }

    fun setCards(setCode: String) = safeApiCall {
        webservice.setCards(setCode)
    }

    fun cards(query: Map<String, String>) = safeApiCall {
        webservice.cards(query)
    }

}