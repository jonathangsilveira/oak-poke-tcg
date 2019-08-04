package br.edu.jgsilveira.portfolio.oakpokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.jgsilveira.portfolio.oakpokedex.ext.rotate
import br.edu.jgsilveira.portfolio.oakpokedex.ext.showIn
import br.edu.jgsilveira.portfolio.oakpokedex.ext.showOut
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var optionsOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fabAdd.setOnClickListener {
            val value = if (optionsOpened)
                135f
            else
                0f
            it.rotate(value)
            showOrHideOptions()
            optionsOpened = !optionsOpened
        }
    }

    private fun showOrHideOptions() {
        if (optionsOpened) {
            fabCall.showOut()
            fabMic.showOut()
        } else {
            fabCall.showIn()
            fabMic.showIn()
        }
    }

}
