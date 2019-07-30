package br.edu.jgsilveira.portfolio.oakpokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.jgsilveira.portfolio.oakpokedex.tcg.data.Sets
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.sets_item.view.*

class SetsAdapter(private val sets: Sets): RecyclerView.Adapter<SetsAdapter.SetsViewHolder>() {

    private var onItemClick: (String, String) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.sets_item, parent, false)
        return SetsViewHolder(itemView = layout)
    }

    override fun getItemCount(): Int = sets.sets.size

    override fun onBindViewHolder(holder: SetsViewHolder, position: Int) = holder.bind(sets.sets[position])

    fun setOnItemClickListener(onItemClick: (setCode: String, backdropUrl: String) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class SetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(set: Sets.Set) {
            itemView.setOnClickListener {
                onItemClick(set.code, set.logoUrl)
            }
            itemView.name.text = set.name
            itemView.series.text = set.series
            Picasso.get()
                .load(set.symbolUrl)
                //Icons made by https://www.flaticon.com/authors/those-icons
                .placeholder(R.drawable.ic_pokeball)
                .into(itemView.symbol)
        }

    }

}