package com.adidas.sports.goal

import android.content.Context
import android.os.Environment

object AppUtils {

    fun getAppCachePath(context: Context): String {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
                && context.externalCacheDir != null) {
            context.externalCacheDir!!.path
        } else {
            context.cacheDir.path
        }
    }
}