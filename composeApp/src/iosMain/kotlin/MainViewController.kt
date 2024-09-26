import androidx.compose.ui.window.ComposeUIViewController
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.areeb.weatherunion.logic.di.LogicComponent
import platform.UIKit.UIViewController

fun MainViewController(
    logicComponent: LogicComponent,
    mapUIViewController: (selectedLocality: LocalityData, localityList: List<LocalityData>, (LocalityData) -> Unit) -> () -> UIViewController,
) = ComposeUIViewController {
    mapViewController = mapUIViewController
    App(logicComponent = logicComponent)
}

lateinit var mapViewController: (selectedLocality: LocalityData, localityList: List<LocalityData>, (LocalityData) -> Unit) -> () -> UIViewController
