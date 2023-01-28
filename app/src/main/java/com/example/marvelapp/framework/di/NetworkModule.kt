package com.example.marvelapp.framework.di

import com.example.marvelapp.BuildConfig
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {

    @Provides
    fun provideHttpLogginInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(
            if(BuildConfig.DEBUG){
                 HttpLoggingInterceptor.Level.BODY
            }else{
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor = httpLoggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
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

}