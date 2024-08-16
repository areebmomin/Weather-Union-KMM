package com.areeb.weatherunion

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                logicComponent = (application as WeatherUnionApplication).applicationComponent
            )
        }
    }
}
