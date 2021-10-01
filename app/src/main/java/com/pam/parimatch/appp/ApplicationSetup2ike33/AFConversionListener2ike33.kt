package com.pam.parimatch.appp.ApplicationSetup2ike33

import com.appsflyer.AppsFlyerConversionListener

class AFConversionListener2ike33 : AppsFlyerConversionListener {
    override fun onConversionDataSuccess(data2ike33: MutableMap<String, Any>?) {
        data2ike33?.run {

            URLParse2ike33.status2ike33 = if (getValue(URLParse2ike33.STATUS_2ike33).toString() == "Organic")
                "Organic" else "Non-organic"

            getValue(
                URLParse2ike33.CAMPAIGN_2ike33
            ).toString()
                .split("||")
                .drop(1)
                .map {
                    it.split(":")[1]
                }.run {
                    URLParse2ike33.key2ike33 = if (size >= 1) this[0] else "empty"
                    URLParse2ike33.secParam2ike33 = if (size >= 2) this[1] else "empty"
                    URLParse2ike33.thirdAttr2ike33 = if (size >= 3) this[2] else "empty"
                }

            URLParse2ike33.firstAttr2ike33 = getValue(URLParse2ike33.ADGROUP_2ike33).toString()
            URLParse2ike33.secAttr2ike33 = getValue(URLParse2ike33.ADSET_2ike33).toString()
            URLParse2ike33.thirdAttr2ike33 = getValue(URLParse2ike33.AF_CHANNEL_2ike33).toString()
            URLParse2ike33.fourthAttr2ike33 = getValue(URLParse2ike33.MEDIA_SOURSE_2ike33).toString()
        }
    }

    override fun onConversionDataFail(p0: String?) {
    }

    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
    }

    override fun onAttributionFailure(p0: String?) {
    }
}