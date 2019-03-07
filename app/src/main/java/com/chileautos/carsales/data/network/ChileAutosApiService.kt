package com.chileautos.carsales.data.network

import com.chileautos.carsales.BuildConfig
import com.chileautos.carsales.data.db.entity.CarPost
import com.chileautos.carsales.data.network.response.StockList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.resocoder.forecastmvvm.data.network.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChileAutosApiService {

    @GET(BuildConfig.LIST_ANNOUNCEMENTS)
    fun getStock(): Deferred<StockList>

    @POST(BuildConfig.SAVED_ITEM)
    fun saveFavorite(@Path("id") id: String, @Body payload: CarPost)

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ChileAutosApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("Authorization", BuildConfig.AUTHORIZATION)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChileAutosApiService::class.java)
        }
    }

}