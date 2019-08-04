package br.edu.jgsilveira.portfolio.oakpokedex.sets

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.Repository
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult

class SetsViewModel(
    application: Application,
    repo: Repository
) : AndroidViewModel(application) {

    private val state = SetsViewState()

    val viewState = Transformations.map(repo.sets()) {
        when (it) {
            is SetsNetworkState.Loading -> state.copy(isLoading = true, result = state.result)
            is SetsNetworkState.Loaded -> state.copy(result = it.data)
            is SetsNetworkState.Error -> {
                val message = when (it.failure) {
                    is NetworkResult.Failure.Response -> {
                        val body = it.failure.body
                        "${body?.status} - ${body?.error}"
                    }
                    is NetworkResult.Failure.Undefined -> it.failure.cause.message
                }
                state.copy(error = message)
            }
        }
    }

}