package org.wit.hillforts.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_hillfort.view.*
import kotlinx.android.synthetic.main.card_hillfort.view.HillfortDescription
import kotlinx.android.synthetic.main.card_hillfort.view.HillfortName
import org.wit.hillforts.R
import org.wit.hillforts.models.HillfortModel

interface HillfortListener {
  fun onHillfortClick(hillfort: HillfortModel)
}

class HillfortAdapter constructor(private var hillforts: List<HillfortModel>,
                                  private val listener: HillfortListener
) : RecyclerView.Adapter<HillfortAdapter.MainHolder>() {

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

    fun bind(hillfort: HillfortModel, listener: HillfortListener) {
      itemView.HillfortName.text = hillfort.name
      itemView.HillfortDescription.text = hillfort.description
      itemView.setOnClickListener { listener.onHillfortClick(hillfort) }
    }
  }

}

