package com.example.marvelapp.framework.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Calendar

@Suppress("MagicNumber")
class AuthorizationInterceptor(
    private val publickey: String,
    private val privateKey: String,
    private val calendar: Calendar
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
         val request = chain.request()
         val requestUrl = request.url

         val ts = (calendar.timeInMillis / 1000L).toString()
         val hash = "$ts$privateKey$publickey".md5()

         val newUrl = requestUrl.newBuilder()
             .addQueryParameter(QUERY_PARAMETER_TIMESTAMP, ts)
             .addQueryParameter(QUERY_PARAMETER_PUBLIC_KEY, publickey)
             .addQueryParameter(QUERY_PARAMETER_HASH, hash)
             .build()

         return chain.proceed(
             request.newBuilder()
                 .url(newUrl)
                 .build()
         )
    }

    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    companion object {
        const val QUERY_PARAMETER_PUBLIC_KEY = "apikey"
        const val QUERY_PARAMETER_HASH = "hash"
        const val QUERY_PARAMETER_TIMESTAMP = "ts"
    }
}
