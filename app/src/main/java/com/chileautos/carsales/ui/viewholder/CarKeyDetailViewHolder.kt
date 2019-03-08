package com.chileautos.carsales.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.details_car_item.view.*

class CarKeyDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(detail: String){
        itemView.textKeyDetail.text = detail
    }
}