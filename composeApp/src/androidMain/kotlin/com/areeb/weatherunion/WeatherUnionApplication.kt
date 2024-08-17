package com.areeb.weatherunion

import android.app.Application
import com.areeb.weatherunion.data.AndroidPlatformContextProvider
import com.areeb.weatherunion.shared.ApplicationComponent
import com.areeb.weatherunion.shared.create

class WeatherUnionApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        ApplicationComponent.create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        AndroidPlatformContextProvider.setContext(applicationContext)
    }
}
