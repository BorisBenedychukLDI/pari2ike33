package com.pam.parimatch.appp.ApplicationSetup2ike33

import android.util.Base64

object URLParse2ike33 {
    var key2ike33: String? = null
    var secParam2ike33: String? = null
    var thirdParam2ike33: String? = null
    var firstAttr2ike33: String? = null
    var secAttr2ike33: String? = null
    var thirdAttr2ike33: String? = null
    var fourthAttr2ike33: String? = null
    var status2ike33: String? = null
    var gaId2ike33: String? = null
    var uid2ike33: String? = null


    var firebaseDefValue2ike33: String? = null
    var firebaseBlackValue2ike33: String? = null
    var firebaseWhiteValue2ike33: String? = null

    const val FIREBASE_DEFAULT_2ike33 = "pamdefaultkey"
    const val FIREBASE_WHITE_2ike33 = "pamwhitepage"
    const val FIREBASE_BLACK_2ike33 = "pamblackpage"

    const val APPSFLYER_KEY_CODED_2ike33 = "WDdjRHdpWTdXZ0dIS1pBYllicFlqZA=="
    const val ONE_SIGNAL_CODED_2ike33 = "OTJiODY0N2ItZjkyNy00M2FlLWI0N2YtZjE0NjIwNDg4ZTE2"

    const val CAMPAIGN_2ike33 = "campaign"
    const val STATUS_2ike33 = "af_status"
    const val ADGROUP_2ike33 = "adgroup"
    const val ADSET_2ike33 = "adset"
    const val AF_CHANNEL_2ike33 = "af_channel"
    const val MEDIA_SOURSE_2ike33 = "media_source"

    const val KEY_2ike33 = "key"
    const val BUNDLE_2ike33 = "bundle"
    const val SUB2_2ike33 = "sub2"
    const val SUB3_2ike33 = "sub3"
    const val SUB4_2ike33 = "sub4"
    const val SUB5_2ike33 = "sub5"
    const val SUB6_2ike33 = "sub6"
    const val SUB7_2ike33 = "sub7"
    const val SUB10_2ike33 = "&sub10="


    fun decodeKeys2ike33 (str2ike33: String) = String(Base64.decode(str2ike33, Base64.DEFAULT))

    fun getSub102ike33() = "${uid2ike33}||$gaId2ike33||${decodeKeys2ike33(APPSFLYER_KEY_CODED_2ike33)}"
}