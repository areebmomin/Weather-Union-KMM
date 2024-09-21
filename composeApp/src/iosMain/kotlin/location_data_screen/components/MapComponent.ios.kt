package location_data_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import mapViewController
import utils.getScreenHeight
import utils.getScreenWidth

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MapComponent() {
    val percent70Height = getScreenHeight().value * 0.7
    UIKitViewController(
        factory = mapViewController,
        modifier = Modifier.size(width = getScreenWidth(), height = percent70Height.dp).border(2.dp, Color.Blue),
    )
}
