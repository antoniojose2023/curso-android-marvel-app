package antoniojoseuchoa.com.core.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Calendar

class AuthorizationInterceptor(
    val publicKey: String,
    val privateKey: String,
    val calendar: Calendar
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
       val request = chain.request()
       val requestUrl = request.url()

        val ts = (calendar.timeInMillis / 1000L).toString()
        val hash = "${ts}${privateKey}$publicKey".md5()

        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(QUERY_PARAMETERS_TS, ts)
            .addQueryParameter(QUERY_PARAMETERS_PUBLICKEY, publicKey)
            .addQueryParameter(QUERY_PARAMETERS_HASH, hash)
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
        const val QUERY_PARAMETERS_TS = "query_parameters_ts"
        const val QUERY_PARAMETERS_PUBLICKEY = "query_parameters_publickey"
        const val QUERY_PARAMETERS_HASH = "query_parameters_hash"
    }
}
