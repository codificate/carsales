package com.chileautos.carsales.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chileautos.carsales.data.network.response.StockList
import com.chileautos.carsales.internal.NoConnectivityException

class CarPostNetworkDataSourceImpl(
    private val chileAutosApiService: ChileAutosApiService
) : CarPostNetworkDataSource {

    private val _downloadedCarPost = MutableLiveData<StockList>()
    override val downloadedCarPost: LiveData<StockList>
        get() = _downloadedCarPost

    override suspend fun fetchCarPosts() {
        try {
            val carPostsFetched = chileAutosApiService.getStock().await()
            _downloadedCarPost.postValue(carPostsFetched)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}