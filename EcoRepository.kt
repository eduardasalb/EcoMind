package com.example.ecomind.repository

import com.example.ecomind.data.TipDao
import com.example.ecomind.model.TipHistoryEntity
import com.example.ecomind.network.WeatherApi
import com.example.ecomind.network.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class EcoRepository(
    private val tipDao: TipDao,
    private val weatherApi: WeatherApi
) {
    suspend fun fetchCurrentWeather(city: String, apiKey: String): WeatherResponse? {
        return withContext(Dispatchers.IO) {
            try {
                weatherApi.getCurrentWeather(city, apiKey)
            } catch (t: Throwable) {
                t.printStackTrace()
                null
            }
        }
    }

    suspend fun saveTipToHistory(text: String) {
        val entity = TipHistoryEntity(text = text)
        withContext(Dispatchers.IO) {
            tipDao.insertTip(entity)
        }
    }

    suspend fun getAllTipHistory() = withContext(Dispatchers.IO) {
        tipDao.getAllHistory()
    }

    companion object {
        fun createWeatherApi(baseUrl: String): WeatherApi {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WeatherApi::class.java)
        }
    }
}
