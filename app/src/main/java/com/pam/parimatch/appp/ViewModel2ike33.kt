package com.pam.parimatch.appp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.pam.parimatch.appp.ApplicationSetup2ike33.URLParse2ike33
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class ViewModel2ike33 : ViewModel() {

    val urlLiveData2ike33 = MutableLiveData<String>()
    val firebaseConfigLiveData2ike33 = MutableLiveData<Boolean>()
    val isInternetPresent2ike33 = MutableLiveData<Boolean>()


    fun isNetworkIsAvailableWebView2ike33 (context2ike33: Context) {
        viewModelScope.launch {
            while (isActive) {
                isInternetPresent2ike33.value = isNetworkIsAvailable2ike33(context2ike33)
                if (!isNetworkIsAvailable2ike33(context2ike33)) {
                    cancel()
                }
                delay(1000)
            }
        }
    }

    fun isNetworkAvailableSplash2ike33 (context2ike33: Context) {
        viewModelScope.launch {
            while (isActive) {
                isInternetPresent2ike33.value = isNetworkIsAvailable2ike33(context2ike33)
                if (isNetworkIsAvailable2ike33(context2ike33)) {
                    cancel()
                }
                delay(1000)
            }
        }
    }

    fun checkManuallyIsNetworkAvailable2ike33 (context2ike33: Context) = isNetworkIsAvailableWebView2ike33(context2ike33)

    private fun isNetworkIsAvailable2ike33(context2ike33: Context): Boolean  {
        val connectivityManager2ike33 = context2ike33.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities2ike33 = connectivityManager2ike33.getNetworkCapabilities(connectivityManager2ike33.activeNetwork) ?: return false
            return networkCapabilities2ike33.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            for (network2ike33 in connectivityManager2ike33.allNetworks) {
                connectivityManager2ike33.getNetworkInfo(network2ike33)?.let {
                    if (it.isConnected) return true
                }
            }
            return false
        }
    }

    fun setupFirebase2ike33 () {
        CoroutineScope(Dispatchers.IO).launch {
            Firebase.remoteConfig.run {
                setConfigSettingsAsync(
                    remoteConfigSettings {
                        minimumFetchIntervalInSeconds = 1000
                    }
                ).await()
                setDefaultsAsync(
                    mapOf(
                        URLParse2ike33.FIREBASE_BLACK_2ike33 to "empty"
                    )
                ).await()
                fetchAndActivate().await()
                URLParse2ike33.firebaseDefValue2ike33 =
                    getString(URLParse2ike33.FIREBASE_DEFAULT_2ike33)
                URLParse2ike33.firebaseBlackValue2ike33 =
                    getString(URLParse2ike33.FIREBASE_BLACK_2ike33)
                URLParse2ike33.firebaseWhiteValue2ike33 =
                    getString(URLParse2ike33.FIREBASE_WHITE_2ike33)
                withContext(Dispatchers.Main) {
                    URLParse2ike33.firebaseWhiteValue2ike33?.let {
                        firebaseConfigLiveData2ike33.value = true
                    }
                }
            }
        }
    }

    fun getUrl2ike33(packageName2ike33: String) {
        if (URLParse2ike33.firebaseBlackValue2ike33 == null || URLParse2ike33.firebaseBlackValue2ike33 == "empty") {
            setUpWhitePage()
        } else {
            if (URLParse2ike33.status2ike33.equals("Non-organic")) {
                if (URLParse2ike33.key2ike33?.length == 20)
                    setUpNonOrganicKey2ike33(packageName2ike33)
                else
                    setupNonOrganicNoKey2ike33(packageName2ike33)
            } else {
                setUpOrganic2ike33(packageName2ike33)
            }
        }
    }

    private fun setupNonOrganicNoKey2ike33(packageName2ike33: String) {
        urlLiveData2ike33.value =
            Uri.parse(URLParse2ike33.firebaseBlackValue2ike33).buildUpon()
                .appendQueryParameter(
                    URLParse2ike33.KEY_2ike33,
                    URLParse2ike33.firebaseDefValue2ike33
                )
                .appendQueryParameter(URLParse2ike33.BUNDLE_2ike33, packageName2ike33)
                .appendQueryParameter(URLParse2ike33.SUB4_2ike33, URLParse2ike33.firstAttr2ike33)
                .appendQueryParameter(URLParse2ike33.SUB5_2ike33, URLParse2ike33.secAttr2ike33)
                .appendQueryParameter(URLParse2ike33.SUB6_2ike33, URLParse2ike33.thirdAttr2ike33)
                .appendQueryParameter(URLParse2ike33.SUB7_2ike33, "Default")
                .toString()
                .plus(URLParse2ike33.SUB10_2ike33)
                .plus(URLParse2ike33.getSub102ike33())
    }

    private fun setUpNonOrganicKey2ike33(packageName2ike33: String) {
        urlLiveData2ike33.value =
            Uri.parse(URLParse2ike33.firebaseBlackValue2ike33).buildUpon()
                .appendQueryParameter(URLParse2ike33.KEY_2ike33, URLParse2ike33.key2ike33)
                .appendQueryParameter(URLParse2ike33.BUNDLE_2ike33, packageName2ike33)
                .appendQueryParameter(URLParse2ike33.SUB2_2ike33, URLParse2ike33.secParam2ike33)
                .appendQueryParameter(URLParse2ike33.SUB3_2ike33, URLParse2ike33.thirdParam2ike33)
                .appendQueryParameter(URLParse2ike33.SUB4_2ike33, URLParse2ike33.firstAttr2ike33)
                .appendQueryParameter(URLParse2ike33.SUB5_2ike33, URLParse2ike33.secAttr2ike33)
                .appendQueryParameter(URLParse2ike33.SUB6_2ike33, URLParse2ike33.thirdAttr2ike33)
                .appendQueryParameter(URLParse2ike33.SUB7_2ike33, URLParse2ike33.fourthAttr2ike33)
                .toString()
                .plus(URLParse2ike33.SUB10_2ike33)
                .plus(URLParse2ike33.getSub102ike33())
    }

    private fun setUpOrganic2ike33(packageName2ike33: String) {
        urlLiveData2ike33.value =
            Uri.parse(URLParse2ike33.firebaseBlackValue2ike33).buildUpon()
                .appendQueryParameter(
                    URLParse2ike33.KEY_2ike33,
                    URLParse2ike33.firebaseDefValue2ike33
                )
                .appendQueryParameter(URLParse2ike33.BUNDLE_2ike33, packageName2ike33)
                .appendQueryParameter(URLParse2ike33.SUB7_2ike33, "Organic")
                .toString()
                .plus(URLParse2ike33.SUB10_2ike33)
                .plus(URLParse2ike33.getSub102ike33())
    }

    private fun setUpWhitePage() {
        urlLiveData2ike33.value = URLParse2ike33.firebaseWhiteValue2ike33
    }
}