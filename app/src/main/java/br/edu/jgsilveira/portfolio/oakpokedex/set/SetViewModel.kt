package br.edu.jgsilveira.portfolio.oakpokedex.set

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.edu.jgsilveira.portfolio.oakpokedex.CardsNetworkState
import br.edu.jgsilveira.portfolio.oakpokedex.cards.CardsViewState
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.Repository
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult

class SetViewModel(
    application: Application,
    val repo: Repository
) : AndroidViewModel(application) {

    private val state = CardsViewState()

    private val setCode: MutableLiveData<String> = MutableLiveData()

    val viewState = Transformations.switchMap(setCode) { code ->
        Transformations.map(repo.set(code)) { networkState ->
            when (networkState) {
                is CardsNetworkState.Loading -> state.copy(isLoading = true)
                is CardsNetworkState.Loaded -> state.copy(cards = networkState.value)
                is CardsNetworkState.Error -> {
                    val message = when (networkState.failure) {
                        is NetworkResult.Failure.Response -> {
                            val body = networkState.failure.body
                            "${body?.status} - ${body?.error}"
                        }
                        is NetworkResult.Failure.Undefined -> networkState.failure.cause.message
                    }
                    state.copy(error = message)
                }
            }
        }
    }

    fun load(code: String) {
        setCode.value = code
    }

}