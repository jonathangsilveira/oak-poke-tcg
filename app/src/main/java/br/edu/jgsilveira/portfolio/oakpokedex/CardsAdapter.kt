package br.edu.jgsilveira.portfolio.oakpokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Cards
import com.squareup.picasso.Picasso

class CardsAdapter(private val cards: Cards): RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return CardsViewHolder(view)
    }

    override fun getItemCount(): Int = cards.cards.size

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) = holder.bind(card = cards.cards[position])

    inner class CardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(card: Cards.Card) {
            Picasso.get()
                .load(card.imageUrl)
                .placeholder(R.drawable.card_back)
                .into(itemView as ImageView)
        }

    }

}