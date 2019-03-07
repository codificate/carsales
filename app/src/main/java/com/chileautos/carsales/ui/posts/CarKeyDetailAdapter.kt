package com.chileautos.carsales.ui.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.chileautos.carsales.R
import kotlinx.android.synthetic.main.details_car_item.view.*

class CarKeyDetailAdapter(
    val context: Context, val keyDetails: List<String>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var detail = keyDetails[position]
        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.details_car_item, parent)
        view.textKeyDetail.text = detail

        return view
    }

    override fun getItem(position: Int): Any {
        return keyDetails[position]
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented")
    }

    override fun getCount(): Int {
        return keyDetails.size
    }
}