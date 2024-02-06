package com.example.retrofitpokemon.data.domain.repository.remote

import com.caverock.androidsvg.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class RetrofitClient() {
    companion object {
        const val HEADER_KEY_TOKEN = "Authorization"
        const val HEADER_VALUE_TOKEN_START = "Bearer "
        private const val COOKIE = "Cookie"
        private const val CTX = "CTX="
        private const val JSESSIONID = "JSESSIONID="

        private const val sha256Pro = "sha256/rBdg34UcmakLyld0b2DbXyFAspIFTmU5XxfW9HbPefk="
        private const val sha256Test = "sha256/i3svhbp3VbQyN0XXy+CpSlZOqm4LbkmfDGTUjoIAzzk="

        private var INSTANCE: RetrofitClient? = null

        @Synchronized
        fun getInstance(): RetrofitClient {
            if (INSTANCE == null) {
                INSTANCE = RetrofitClient()
            }
            return INSTANCE!!
        }
    }

    private val retrofit: Retrofit
    private val remoteApiService: RemoteApiService

    init {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        httpClient
            .connectTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)

        httpClient.interceptors().clear()

        if (BuildConfig.DEBUG) {
            // Creamos un interceptor y le indicamos el log level a usar
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }

        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
        remoteApiService = retrofit.create(RemoteApiService::class.java)
    }

    fun getApiServices(): RemoteApiService {
        return remoteApiService
    }
}