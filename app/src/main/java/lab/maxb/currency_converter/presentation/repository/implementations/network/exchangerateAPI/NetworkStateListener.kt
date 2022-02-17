package lab.maxb.currency_converter.presentation.repository.implementations.network.exchangerateAPI

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData

class NetworkStateListener(context: Context) {
    private val context = context.applicationContext

    val connectionState = MutableLiveData<Boolean>()

    //region used on api < 24
    private val intentFilter by lazy {
        IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    }

    private val networkBroadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                connectionState.postValue(isConnected)
            }
        }
    }
    //endregion

    private val networkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                connectionState.postValue(true)
            }

            override fun onLost(network: Network) {
                connectionState.postValue(false)
            }
        }
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            connectivityManager?.registerDefaultNetworkCallback(networkCallback)
        else
            context.registerReceiver(networkBroadcastReceiver, intentFilter)
    }

    val isConnected
        get() = connectivityManager
            ?.activeNetworkInfo
            ?.isConnectedOrConnecting == true

    private val connectivityManager
        get() = getSystemService(
            context,
            ConnectivityManager::class.java
        )
}