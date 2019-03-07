package com.chileautos.carsales.data.repository

import androidx.lifecycle.LiveData
import com.chileautos.carsales.data.db.entity.CarPost

interface CarSalesRepository {
    suspend fun getAllCarPost(): LiveData<List<CarPost>>
}