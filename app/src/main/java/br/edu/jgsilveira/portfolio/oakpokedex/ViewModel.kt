package br.edu.jgsilveira.portfolio.oakpokedex

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telecom.ConnectionService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel(
    application: Application,
    private val repo: Repository
) : AndroidViewModel(application) {

    val isLoading: MutableLiveData<Boolean> by lazyOf(MutableLiveData(false))

    fun load() {
        viewModelScope.launch(Dispatchers.Main) {
            isLoading.value = true
            withContext(Dispatchers.IO) {
                repo.loadTypes()
                repo.loadSubtypes()
            }
            isLoading.value = false
        }
    }

    private inline fun <reified T> systemService(serviceName: String): T =
        getApplication<App>().applicationContext.getSystemService(serviceName) as T

    private fun isConnected(): Boolean {
        val service = systemService<ConnectivityManager>(Context.CONNECTIVITY_SERVICE)
        val networkInfo: NetworkInfo? = service.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}