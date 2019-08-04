package br.edu.jgsilveira.portfolio.oakpokedex

import android.app.Application
import br.edu.jgsilveira.portfolio.oakpokedex.set.SetViewModel
import br.edu.jgsilveira.portfolio.oakpokedex.sets.SetsViewModel
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.Endpoint
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            val module = module(createdAtStart = true) {
                single {
                    Endpoint()
                }
                single {
                    Repository(endpoint = get())
                }
                viewModel(override = true) {
                    ViewModel(application = androidApplication(), repo = get())
                }
                viewModel(override = true) {
                    SetsViewModel(
                        application = androidApplication(),
                        repo = get()
                    )
                }
                viewModel(override = true) {
                    SetViewModel(
                        application = androidApplication(),
                        repo = get()
                    )
                }
            }
            modules(module)
        }
    }

}