package com.bookslibrary.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.util.UUID

/**
 * Created by Divya V on 21-10-2024.
 */
class AppUtils {

    fun isInterConnectionIsAvailable(con: Context): Boolean {
        var haveConnectedWifi = false
        var haveConnectedMobile = false

        val cm = con.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: Array<NetworkInfo?> = cm.allNetworkInfo
        for (ni in netInfo) {
            if (ni?.typeName.equals("WIFI", ignoreCase = true) && ni?.isConnected==true) {
                haveConnectedWifi = true
            }
            if (ni?.typeName.equals("MOBILE", ignoreCase = true) && ni?.isConnected==true) {
                haveConnectedMobile = true
            }
        }
        return haveConnectedWifi || haveConnectedMobile
    }
    companion object {
        fun generateUniqueId(): Int {
            return UUID.randomUUID().hashCode() // Convert UUID to Int using hashCode()
        }
    }
}