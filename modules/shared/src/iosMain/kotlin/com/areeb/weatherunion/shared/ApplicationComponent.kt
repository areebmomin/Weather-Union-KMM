package com.areeb.weatherunion.shared

import com.areeb.weatherunion.core.di.ApplicationScope
import com.areeb.weatherunion.core.di.CoreComponent
import com.areeb.weatherunion.data.di.DataComponent
import com.areeb.weatherunion.data.di.IosDataComponent
import com.areeb.weatherunion.logic.di.LogicComponent
import me.tatarka.inject.annotations.Component

@ApplicationScope
@Component
abstract class ApplicationComponent :
    CoreComponent,
    DataComponent,
    LogicComponent,
    IosDataComponent {

    companion object

}
