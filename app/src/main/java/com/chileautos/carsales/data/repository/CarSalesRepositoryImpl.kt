package com.chileautos.carsales.data.repository

import androidx.lifecycle.LiveData
import com.chileautos.carsales.data.db.CarPostDao
import com.chileautos.carsales.data.db.entity.CarPost
import com.chileautos.carsales.data.db.entity.DisplayPrice
import com.chileautos.carsales.data.network.CarPostNetworkDataSource
import com.chileautos.carsales.data.network.response.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarSalesRepositoryImpl(
    private val carPostDao: CarPostDao,
    private val carPostNetworkDataSource: CarPostNetworkDataSource) : CarSalesRepository {

    init {
        carPostNetworkDataSource.apply {
            downloadedCarPost.observeForever { response ->
                saveCartPost(response.result.toCarPost())
            }
        }
    }

    private fun saveCartPost(carPosts: List<CarPost>) {
        GlobalScope.launch(Dispatchers.IO) {
            carPostDao.bulkCarPostInsert(carPosts)
        }
    }

    override suspend fun getAllCarPost(): LiveData<List<CarPost>> {
        return withContext(Dispatchers.IO) {
            fetchCarPost()
            return@withContext carPostDao.getAll()
        }
    }

    private suspend fun fetchCarPost() {

        val carPost = carPostDao.getFirst()

        if (carPost.value?.id == null) {
            carPostNetworkDataSource.fetchCarPosts()
        }
    }

    private fun List<Result>.toCarPost() : List<CarPost> {
        return this.map { item -> CarPost(null,
            item.networkId, DisplayPrice( item.displayPrice.preInfo, item.displayPrice.price ),
            item.displayLocation, item.displayTitle, item.keyDetails, item.mainPhoto,
            item.manufacturerCertifications, item.photos, item.siloTypeColour, item.siloTypeFriendlyName,
            item.webDetailsUrl) }
    }
}