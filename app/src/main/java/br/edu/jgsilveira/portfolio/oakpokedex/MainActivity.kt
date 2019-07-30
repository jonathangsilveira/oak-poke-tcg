package br.edu.jgsilveira.portfolio.oakpokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.isLoading.observe(this) {
            render(isLoading = it)
        }
        viewModel.load()
    }

    private fun render(isLoading: Boolean?) {
        progressBar.visibility = if (isLoading == true) View.VISIBLE else View.GONE
    }

}
