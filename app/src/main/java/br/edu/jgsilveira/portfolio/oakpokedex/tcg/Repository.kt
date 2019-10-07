package br.edu.jgsilveira.portfolio.oakpokedex.tcg

import androidx.lifecycle.liveData
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Subtypes
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Types
import kotlinx.coroutines.Dispatchers

class Repository(private val endpoint: Endpoint) {

    private var types: Types? = null

    private var subtypes: Subtypes? = null

    fun loadTypes() = liveData(Dispatchers.IO) {
        if (types == null) {
            emit(NetworkState.Loading())
            when (val result = endpoint.types()) {
                is NetworkResult.Success -> {
                    types = result.value
                    emit(NetworkState.Loaded(types))
                }
                is NetworkResult.Failure -> emit(NetworkState.Error(result))
            }
        } else
            emit(NetworkState.Loaded(types))
    }

    fun loadSubtypes() = liveData(Dispatchers.IO) {
        if (subtypes == null) {
            emit(NetworkState.Loading())
            when (val result = endpoint.subtypes()) {
                is NetworkResult.Success -> {
                    subtypes = result.value
                    emit(NetworkState.Loaded(subtypes))
                }
                is NetworkResult.Failure -> {
                    emit(NetworkState.Error(result))
                }
            }
        } else
            emit(NetworkState.Loaded(subtypes))
    }

    fun sets() = liveData(Dispatchers.IO) {
        emit(NetworkState.Loading())
        when (val result = endpoint.sets(mapOf())) {
            is NetworkResult.Success -> emit(NetworkState.Loaded(result.value))
            is NetworkResult.Failure -> emit(NetworkState.Error(result))
        }
    }

    fun set(setCode: String) = liveData(Dispatchers.IO) {
        emit(NetworkState.Loading())
        when (val result = endpoint.setCards(setCode)) {
            is NetworkResult.Success -> emit(NetworkState.Loaded(result.value))
            is NetworkResult.Failure -> emit(NetworkState.Error(result))
        }
    }

}