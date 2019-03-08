package com.chileautos.carsales.ui.model

import androidx.lifecycle.ViewModel
import com.chileautos.carsales.data.repository.CarSalesRepository
import com.chileautos.carsales.internal.lazyDeferred

class CarPostViewModel(
    private val carSalesRepository: CarSalesRepository
) : ViewModel() {

    val fetchPosts by lazyDeferred {
        carSalesRepository.getAllCarPost()
    }
}