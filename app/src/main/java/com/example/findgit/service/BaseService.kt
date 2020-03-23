package com.example.findgit.service

import android.content.Context
import android.net.ConnectivityManager
import com.example.findgit.BuildConfig

open class BaseService {

    companion object {

        private val TAG = BaseService::class.java.simpleName

        const val URL_BASE_GITHUB: String = BuildConfig.URL_BASE_GITHUB

        fun isNetworkAvailable(context: Context): Boolean {

            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo

            return networkInfo != null && networkInfo.isConnected
        }

    }
}