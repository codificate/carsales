package com.chileautos.carsales.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chileautos.carsales.data.repository.CarSalesRepository

class CarPostViewModelFactory(
    private val carSalesRepository: CarSalesRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarPostViewModel(carSalesRepository) as T
    }
}