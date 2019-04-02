package com.adidas.sports.goal

import android.content.Context
import android.net.NetworkInfo
import android.net.ConnectivityManager



object NetworkUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connManager.activeNetworkInfo
        return netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED
    }
}