package br.edu.jgsilveira.portfolio.oakpokedex.set

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.edu.jgsilveira.portfolio.oakpokedex.cards.CardsViewState
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.NetworkState
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.Repository
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Cards
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.NetworkResult

class SetViewModel(
    application: Application,
    val repo: Repository
) : AndroidViewModel(application) {

    private val setCode: MutableLiveData<String> = MutableLiveData()

    val viewState = Transformations.switchMap(setCode) { code ->
        Transformations.map(repo.set(code)) { networkState ->
            when (networkState) {
                is NetworkState.Loading -> CardsViewState.loading()
                is NetworkState.Loaded -> CardsViewState.loaded(networkState.value)
                is NetworkState.Error -> handleNetworkError(networkState)
            }
        }
    }

    fun load(code: String) {
        setCode.value = code
    }

    private fun handleNetworkError(networkState: NetworkState.Error<Cards>): CardsViewState {
        return when (networkState.cause) {
            is NetworkResult.Failure.Response -> CardsViewState.error("Sorry but something went wrong!")
            else -> CardsViewState.error("Something unexpected just happened :(")
        }
    }

}
