package com.chileautos.carsales.ui.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.chileautos.carsales.R
import com.chileautos.carsales.internal.glide.GlideApp

class CarPhotosViewPagerAdapter(
    val context: Context, val photos: List<String>) : PagerAdapter(){

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return photos.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater
            .from(context).inflate(R.layout.car_post_photo_item,container,false)

        val img: ImageView = view.findViewById(R.id.imagePhotoCar)

        setImage(img, position)

        container.addView(view)
        return view
    }

    private fun setImage(imageView: ImageView, position: Int) {
        GlideApp.with(context)
            .load(photos[position])
            .into(imageView)
    }
}