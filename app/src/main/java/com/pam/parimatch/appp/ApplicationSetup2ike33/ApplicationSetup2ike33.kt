package com.pam.parimatch.appp.ApplicationSetup2ike33

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplicationSetup2ike33 : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        CoroutineScope(Dispatchers.IO).launch {
         try {
             URLParse2ike33.gaId2ike33 = AdvertisingIdClient.getAdvertisingIdInfo(this@ApplicationSetup2ike33)?.id.also {
             }
         } catch (e : Exception ) {
             return@launch
         }
        }
        AppsFlyerLib.getInstance().run {
            URLParse2ike33.uid2ike33 = getAppsFlyerUID(this@ApplicationSetup2ike33)
            init(
                URLParse2ike33.decodeKeys2ike33(URLParse2ike33.APPSFLYER_KEY_CODED_2ike33),
                AFConversionListener2ike33(),
                this@ApplicationSetup2ike33
            )
            start(this@ApplicationSetup2ike33)
        }
        OneSignal.initWithContext(this)
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(URLParse2ike33.decodeKeys2ike33(URLParse2ike33.ONE_SIGNAL_CODED_2ike33))
    }
}