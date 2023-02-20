package com.example.marvelapp.framework.di

import com.example.marvelapp.BuildConfig
import com.example.marvelapp.framework.network.MarvelApi
import com.example.marvelapp.framework.network.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val TIME_OUT_CONNECT = 15L
        private const val TIME_OUT_READ = 15L
    }

    @Provides
    fun provideHttpLogginInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(
            if (BuildConfig.DEBUG) {
                 HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    @Provides
    @Suppress("MaximumLineLength")
    fun provideHttpAutthorizationInterceptor() =
        AuthorizationInterceptor(BuildConfig.PUBLIC_KEY,
            BuildConfig.PRIVATE_KEY,
            Calendar.getInstance(TimeZone.getTimeZone("UTC")))

    @Provides
    @Suppress("ParameterListWrapping")
    fun provideOkHttpClient(
        authorizationInterceptor: AuthorizationInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor

    ) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor = httpLoggingInterceptor)
            .addInterceptor(interceptor = authorizationInterceptor)
            .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideGsonConverterFactory() = GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory) =
            Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .baseUrl(BuildConfig.BASE_URL)
                .build()
                .create(MarvelApi::class.java)
}
