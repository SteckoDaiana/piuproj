package com.example.colocviu2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var text: List<Entity>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemText: TextView = itemView.findViewById(R.id.text)
        private val itemImage: Button = itemView.findViewById(R.id.isFav)
        private val cardView: CardView = itemView.findViewById(R.id.cardView)

        fun setData(entity: Entity) {
            itemText.text = entity.activity
            itemImage.setOnClickListener(View.OnClickListener { // Perform action on click
                if (!entity.isFavourite) {
                    entity.isFavourite = true
                    cardView.setBackgroundColor(Color.parseColor("#CC0000"))
                } else {
                    entity.isFavourite = false
                    cardView.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return text.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(text[position])
    }

}
