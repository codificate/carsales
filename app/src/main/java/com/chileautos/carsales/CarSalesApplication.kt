package com.chileautos.carsales

import android.app.Application
import com.chileautos.carsales.data.db.CarSaleDatabase
import com.chileautos.carsales.data.network.CarPostNetworkDataSource
import com.chileautos.carsales.data.network.CarPostNetworkDataSourceImpl
import com.chileautos.carsales.data.network.ChileAutosApiService
import com.chileautos.carsales.data.repository.CarSalesRepository
import com.chileautos.carsales.data.repository.CarSalesRepositoryImpl
import com.chileautos.carsales.ui.posts.CarPostViewModelFactory
import com.resocoder.forecastmvvm.data.network.ConnectivityInterceptor
import com.resocoder.forecastmvvm.data.network.ConnectivityInterceptorImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CarSalesApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@CarSalesApplication))

        bind() from singleton { CarSaleDatabase(instance()) }
        bind() from singleton { instance<CarSaleDatabase>().carPostDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ChileAutosApiService(instance()) }
        bind<CarPostNetworkDataSource>() with singleton { CarPostNetworkDataSourceImpl(instance()) }
        bind() from provider { CarPostViewModelFactory(instance()) }
        bind<CarSalesRepository>() with singleton { CarSalesRepositoryImpl(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}