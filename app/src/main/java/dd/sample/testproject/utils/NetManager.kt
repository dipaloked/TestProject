package dd.sample.testproject.utils

import android.content.Context
import android.net.ConnectivityManager

class NetManager{

    companion object {
        fun isConnected(applicationContext: Context): Boolean{
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
    }
}