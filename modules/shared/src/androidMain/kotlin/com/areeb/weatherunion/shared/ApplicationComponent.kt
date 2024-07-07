package com.areeb.weatherunion.shared

import android.content.Context
import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.core.di.CoreComponent
import com.areeb.weatherunion.data.di.AndroidDataComponent
import com.areeb.weatherunion.data.di.DataComponent
import com.areeb.weatherunion.logic.di.LogicComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@ApplicationScope
@Component
abstract class ApplicationComponent(
    @get:Provides val context: Context,
) : CoreComponent, DataComponent, LogicComponent, AndroidDataComponent {

    companion object

}
