package org.wit.hillforts.views.favouriteview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_hillfort.view.*
import kotlinx.android.synthetic.main.card_hillfort.view.HillfortDescription
import kotlinx.android.synthetic.main.card_hillfort.view.HillfortName
import org.wit.hillforts.R
import org.wit.hillforts.helpers.readImageFromPath
import org.wit.hillforts.models.HillfortModel

interface FavouriteListener {
  fun onHillfortClick(hillfort: HillfortModel)
}

class FavouritesAdapter constructor(private var hillforts: List<HillfortModel>,
                                  private val listener: FavouriteListener
) : RecyclerView.Adapter<FavouritesAdapter.MainHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(
      LayoutInflater.from(parent?.context).inflate(
        R.layout.card_hillfort,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val hillforts = hillforts[holder.adapterPosition]
    holder.bind(hillforts, listener)
  }

  override fun getItemCount(): Int = hillforts.size

  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(hillfort: HillfortModel, listener: FavouriteListener) {
      itemView.HillfortName.text = hillfort.name
      itemView.HillfortDescription.text = hillfort.description
      itemView.ratingBar.rating = hillfort.rating.toFloat()
      if (hillfort.favourite) {
        itemView.Favourite.setVisibility(View.VISIBLE)
      } else{
        itemView.Favourite.setVisibility(View.INVISIBLE)
      }
      Glide.with(itemView.context).load(hillfort.image).into(itemView.imageIcon)
      itemView.setOnClickListener { listener.onHillfortClick(hillfort) }
    }
  }

}

