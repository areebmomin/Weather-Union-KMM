package com.areeb.weatherunion

import App
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )

        val applicationComponent = (application as WeatherUnionApplication).applicationComponent
        val coroutineDispatchers = applicationComponent.coroutineDispatchers

        setContent {
            App(
                logicComponent = applicationComponent,
                coroutineDispatchers = coroutineDispatchers,
            )
        }
    }
}
