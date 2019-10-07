package br.edu.jgsilveira.portfolio.oakpokedex.sets

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.NetworkState
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.Repository
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Sets

class SetsViewModel(
    application: Application,
    repo: Repository
) : AndroidViewModel(application) {

    val viewState = Transformations.map(repo.sets()) { state ->
        when (state) {
            is NetworkState.Loading -> SetsViewState.loading()
            is NetworkState.Loaded -> SetsViewState.loaded(state.value)
            is NetworkState.Error -> handleNetworkError(state)
        }
    }

    private fun handleNetworkError(state: NetworkState.Error<Sets>): SetsViewState {
        return when (state.cause) {
            is NetworkResult.Failure.Response -> SetsViewState.error("Sorry but something went wrong!")
            else -> SetsViewState.error("Something unexpected just happened :(")
        }
    }

}