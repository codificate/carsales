package com.chileautos.carsales.ui.posts

import com.chileautos.carsales.R
import com.chileautos.carsales.data.db.entity.CarPost
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.car_post_item.*
import kotlinx.android.synthetic.main.car_post_list_fragment.*

class CarPostItem( val carPost: CarPost ) : Item() {

    var carKeyDetailAdapter: CarKeyDetailAdapter? = null
    var carPhotosViewPagerAdapter: CarPhotosViewPagerAdapter? = null

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateTitle()
            updatePrice()
            updateDetailsCar()
            updatePhotosCar()
        }
    }

    override fun getLayout() = R.layout.car_post_item

    private fun ViewHolder.updateTitle() {
        textTitleCar.text = carPost.displayTitle
    }

    private fun ViewHolder.updatePrice() {
        textPriceCar.text = carPost.displayPrice.price
    }

    private fun ViewHolder.updateDetailsCar() {
        carKeyDetailAdapter = CarKeyDetailAdapter(containerView.context, carPost.keyDetails)
        detailsCar.adapter = carKeyDetailAdapter
    }

    private fun ViewHolder.updatePhotosCar() {
        carPhotosViewPagerAdapter = CarPhotosViewPagerAdapter(containerView.context, carPost.photos)
        photosCarViewPager.adapter = carPhotosViewPagerAdapter
    }
}