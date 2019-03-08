package com.chileautos.carsales.ui.viewholder

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.chileautos.carsales.R
import com.chileautos.carsales.data.db.entity.CarPost
import com.chileautos.carsales.ui.adapter.CarKeyDetailAdapter
import com.chileautos.carsales.ui.adapter.CarPhotosViewPagerAdapter
import com.chileautos.carsales.utility.GridItemDecoration
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.car_post_item.*

class CarPostItem( val carPost: CarPost ) : Item() {
    var carPhotosViewPagerAdapter: CarPhotosViewPagerAdapter? = null

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateTitle()
            updatePrice()
            updateDetailsCar()
            updatePhotosCar()
            updateTyStablishment()
            imgFavoriteCar.setOnClickListener { Toast.makeText(viewHolder.containerView.context, carPost.networkId, Toast.LENGTH_SHORT).show() }
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

        detailsCar.layoutManager = GridLayoutManager(containerView.context, 2)
        detailsCar.addItemDecoration(GridItemDecoration(10, 2))
        val carKeyDetailAdapter = CarKeyDetailAdapter()
        carKeyDetailAdapter.setKeyDetails(carPost.keyDetails)
        detailsCar.adapter = carKeyDetailAdapter
    }

    private fun ViewHolder.updatePhotosCar() {
        carPhotosViewPagerAdapter =
            CarPhotosViewPagerAdapter(containerView.context, carPost.photos)
        photosCarViewPager.adapter = carPhotosViewPagerAdapter
        dotsIndicator.setViewPager(photosCarViewPager)
    }

    private fun ViewHolder.updateTyStablishment() {
        textTypeStablishment.text = carPost.siloTypeFriendlyName
    }
}