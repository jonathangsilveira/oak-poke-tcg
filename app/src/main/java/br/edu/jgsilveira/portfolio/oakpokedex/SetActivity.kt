package br.edu.jgsilveira.portfolio.oakpokedex

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_set.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SetActivity : AppCompatActivity() {

    private val viewModel: SetViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set)
        setupRecyclerView()
        //loadBackdropImage()
        viewModel.viewState.observe(this) {
            render(state = it)
        }
        viewModel.load(intent.getStringExtra(SET_CODE))
    }

    private fun loadBackdropImage() {
        Picasso.get()
            .load(intent.getStringExtra(BACKDROP_URL))
            .placeholder(R.drawable.ic_tcg_logo)
            .into(app_bar_image)
    }

    private fun render(state: CardsViewState) {
        loading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        if (state.cards != null)
            cardsList.adapter = CardsAdapter(state.cards!!)
        if (state.error != null)
            Snackbar.make(cardsList, state.error.orEmpty(), Snackbar.LENGTH_LONG).show()
    }

    private fun setupRecyclerView() {
        val cardColumns = resources.getInteger(R.integer.card_columns)
        cardsList.layoutManager = GridLayoutManager(
            this,
            cardColumns,
            RecyclerView.VERTICAL,
            false
        )
    }

    companion object {

        private const val SET_CODE = "setCode"

        private const val BACKDROP_URL = "backdropUrl"

        fun newIntent(parent: Context, setCode: String, backdropUrl: String): Intent {
            return Intent(parent, SetActivity::class.java).apply {
                putExtra(SET_CODE, setCode)
                putExtra(BACKDROP_URL, backdropUrl)
            }
        }

    }

}
