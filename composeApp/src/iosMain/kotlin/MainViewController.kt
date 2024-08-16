import androidx.compose.ui.window.ComposeUIViewController
import com.areeb.weatherunion.logic.di.LogicComponent

fun MainViewController(
    logicComponent: LogicComponent,
) = ComposeUIViewController {
    App(logicComponent = logicComponent)
}
