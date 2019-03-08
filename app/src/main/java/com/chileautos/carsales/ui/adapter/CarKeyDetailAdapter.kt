package com.chileautos.carsales.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chileautos.carsales.R
import com.chileautos.carsales.ui.viewholder.CarKeyDetailViewHolder

class CarKeyDetailAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var keyDetails = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarKeyDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.details_car_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return keyDetails.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val keyDetailViewHolder = holder as CarKeyDetailViewHolder
        keyDetailViewHolder.bind(keyDetails[position])
    }

    fun setKeyDetails(keyDetails: List<String>){
        this.keyDetails = keyDetails
        notifyDataSetChanged()
    }
}