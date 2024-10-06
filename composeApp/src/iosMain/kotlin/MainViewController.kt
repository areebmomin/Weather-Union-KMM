import androidx.compose.ui.window.ComposeUIViewController
import com.areeb.weatherunion.core.coroutines.CoroutineDispatchers
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.logic.di.LogicComponent
import platform.UIKit.UIViewController

fun MainViewController(
    logicComponent: LogicComponent,
    coroutineDispatchers: CoroutineDispatchers,
    mapUIViewController: (
        selectedLocality: LocalityData,
        localityList: List<LocalityData>,
        (LocalityData) -> Unit,
    ) -> () -> UIViewController,
) = ComposeUIViewController {
    mapViewController = mapUIViewController

    App(logicComponent = logicComponent, coroutineDispatchers = coroutineDispatchers)
}

lateinit var mapViewController: (
    selectedLocality: LocalityData,
    localityList: List<LocalityData>,
    (LocalityData) -> Unit,
) -> () -> UIViewController
