package br.edu.jgsilveira.portfolio.oakpokedex.tcg

import android.util.Log
import androidx.lifecycle.liveData
import br.edu.jgsilveira.portfolio.oakpokedex.CardsNetworkState
import br.edu.jgsilveira.portfolio.oakpokedex.sets.SetsNetworkState
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.*
import kotlinx.coroutines.Dispatchers

class Repository(private val endpoint: Endpoint) {

    private var types: Types? = null

    private var subtypes: Subtypes? = null

    fun loadTypes(): Types? {
        if (types == null) {
            when (val result = endpoint.types()) {
                is NetworkResult.Success -> types = result.value
                is NetworkResult.Failure.Response -> {
                    Log.i(TAG, "Fail to fetch types! Code: ${result.body?.status} - Message: ${result.body?.error}")
                }
                is NetworkResult.Failure.Undefined -> {
                    Log.e(TAG, "Fail to fetch types!", result.cause)
                }
            }
        }
        return types
    }

    fun loadSubtypes(): Subtypes? {
        if (subtypes == null)
            when (val result = endpoint.subtypes()) {
                is NetworkResult.Success -> subtypes = result.value
                is NetworkResult.Failure.Response -> {
                    Log.i(TAG, "Fail to fetch subtypes! Code: ${result.body?.status} - Message: ${result.body?.error}")
                }
                is NetworkResult.Failure.Undefined -> {
                    Log.e(TAG, "Fail to fetch subtypes!", result.cause)
                }
            }
        return subtypes
    }

    fun sets() = liveData(Dispatchers.IO) {
        emit(SetsNetworkState.Loading)
        when (val result = endpoint.sets(mapOf())) {
            is NetworkResult.Success<*> -> emit(SetsNetworkState.Loaded(data = result.value as Sets))
            is NetworkResult.Failure -> emit(SetsNetworkState.Error(failure = result))
        }
    }

    fun set(setCode: String) = liveData(Dispatchers.IO) {
        emit(CardsNetworkState.Loading)
        when (val result = endpoint.setCards(setCode)) {
            is NetworkResult.Success<*> -> emit(CardsNetworkState.Loaded(result.value as Cards))
            is NetworkResult.Failure -> emit(CardsNetworkState.Error(result))
        }
    }

    companion object {

        private const val TAG = "Repository"

    }

}