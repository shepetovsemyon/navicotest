package ru.shepetov.navicotest.di

import android.util.Log.DEBUG
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.shepetov.navicotest.BuildConfig.ATTRACTIONS_API
import ru.shepetov.navicotest.NetworkConfig
import ru.shepetov.navicotest.network.AttractionsApi
import java.io.File
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { NetworkConfig() }

    single {
        val config = get<NetworkConfig>()

        val dispatcher = Dispatcher().apply {
            maxRequests = config.maxRequests
            maxRequestsPerHost = config.maxRequestPerHost
        }

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .readTimeout(config.readTimeoutSeconds, TimeUnit.SECONDS)
                .writeTimeout(config.writeTimeoutSeconds, TimeUnit.SECONDS)
                .connectTimeout(config.connectTimeoutSeconds, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
    }

    single {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        Retrofit.Builder()
                .client(get())
                .baseUrl(ATTRACTIONS_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    single {
        get<Retrofit>().create(AttractionsApi::class.java)
    }
}