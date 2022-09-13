package com.vanaco.fuael10

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FuaEl10:Application(){
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}