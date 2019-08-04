package br.edu.jgsilveira.portfolio.oakpokedex.sets

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.jgsilveira.portfolio.oakpokedex.R
import br.edu.jgsilveira.portfolio.oakpokedex.set.SetActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sets.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SetsActivity : AppCompatActivity() {

    private val viewModel: SetsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sets)
        setupRecyclerView()
        viewModel.viewState.observe(this) {
            render(state = it)
        }
    }

    private fun render(state: SetsViewState) {
        loading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        if (state.result != null)
            setsList.adapter = SetsAdapter(state.result).apply {
                setOnItemClickListener { setCode, setName, backdropUrl ->
                    callSet(setCode, setName, backdropUrl)
                }
            }
        if (!state.error.isNullOrEmpty())
            Snackbar.make(setsList, state.error.orEmpty(), Snackbar.LENGTH_LONG).show()
    }

    private fun callSet(setCode: String, setName: String, backdropUrl: String) {
        startActivity(
            SetActivity.newIntent(
                this@SetsActivity,
                setCode,
                setName,
                backdropUrl
            )
        )
    }

    private fun setupRecyclerView() {
        setsList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

}
