package com.chileautos.carsales.data.network

import androidx.lifecycle.LiveData
import com.chileautos.carsales.data.network.response.StockList

interface CarPostNetworkDataSource {
    val downloadedCarPost: LiveData<StockList>

    suspend fun fetchCarPosts()
}