package com.areeb.weatherunion

import android.app.Application
import com.areeb.weatherunion.di.ApplicationComponentProvider
import com.areeb.weatherunion.shared.ApplicationComponent
import com.areeb.weatherunion.shared.create

class WeatherUnionApplication : Application(), ApplicationComponentProvider {

    override val applicationComponent by lazy {
        ApplicationComponent.create()
    }
}
