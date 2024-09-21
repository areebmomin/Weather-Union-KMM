import androidx.compose.ui.window.ComposeUIViewController
import com.areeb.weatherunion.logic.di.LogicComponent
import platform.UIKit.UIViewController

fun MainViewController(
    logicComponent: LogicComponent,
    mapUIViewController: () -> UIViewController
) = ComposeUIViewController {
    mapViewController = mapUIViewController
    App(logicComponent = logicComponent)
}

lateinit var mapViewController: () -> UIViewController
