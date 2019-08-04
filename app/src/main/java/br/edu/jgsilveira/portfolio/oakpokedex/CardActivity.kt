package br.edu.jgsilveira.portfolio.oakpokedex

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_card.*

class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        Picasso.get()
            .load(intent.getStringExtra(IMAGE_URL))
            .placeholder(R.drawable.card_back)
            .into(cardImage)
    }

    companion object {

        private const val ID = "ID"

        private const val NAME = "NAME"

        private const val IMAGE_URL = "IMAGE_URL"

        fun newIntent(
            parent: Context,
            id: String,
            name: String,
            imageUrl: String
        ) = Intent(parent, CardActivity::class.java).apply {
            putExtra(ID, id)
            putExtra(NAME, name)
            putExtra(IMAGE_URL, imageUrl)
        }

    }

}
