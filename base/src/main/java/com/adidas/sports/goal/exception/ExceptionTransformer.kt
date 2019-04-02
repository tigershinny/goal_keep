package com.adidas.sports.goal.exception

import android.database.sqlite.SQLiteException
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import javax.net.ssl.SSLHandshakeException


object ExceptionTransformer {
    private const val UNAUTHORIZED = 401
    private const val FORBIDDEN = 403
    private const val NOT_FOUND = 404
    private const val REQUEST_TIMEOUT = 408
    private const val INTERNAL_SERVER_ERROR = 500
    private const val BAD_GATEWAY = 502
    private const val SERVICE_UNAVAILABLE = 503
    private const val GATEWAY_TIMEOUT = 504


    fun error(e: Throwable): AccessThrowable = when(e){
        is HttpException -> AccessThrowable(ERROR.ERROR_HTTP, when (e.code()) {
            UNAUTHORIZED, FORBIDDEN,
            NOT_FOUND, REQUEST_TIMEOUT,
            GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR,
            BAD_GATEWAY, SERVICE_UNAVAILABLE -> "network connect error"
            else -> "network error"
        })
        is ServerException -> AccessThrowable(e.code, e.message)
        is JsonParseException,
        is JSONException,
        is ParseException,
        is JsonIOException -> AccessThrowable(ERROR.ERROR_PARSE, "parse error")
        is ConnectException,
        is SocketTimeoutException,
        is UnknownHostException -> AccessThrowable(ERROR.ERROR_NETWORK, "connect error")
        is SSLHandshakeException -> AccessThrowable(ERROR.ERROR_SSL, "SSL error")
        is SQLiteException -> AccessThrowable(ERROR.ERROR_SQL, "db error")
        is SocketException -> AccessThrowable(ERROR.SOCKET_ERROR, "socket error")
        else -> {
            AccessThrowable(ERROR.UNKNOWN, "unknown error")
        }
    }
}


