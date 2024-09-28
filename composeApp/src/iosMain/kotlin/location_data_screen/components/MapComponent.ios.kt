package location_data_screen.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.unit.dp
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import kotlinx.cinterop.ExperimentalForeignApi
import mapViewController
import utils.getScreenHeight
import utils.getScreenWidth

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MapComponent(
    localityList: List<LocalityData>,
    selectedLocality: LocalityData,
    onItemClicked: (LocalityData) -> Unit,
) {
    val percent70Height = getScreenHeight().value * 0.6

    if (localityList.isNotEmpty()) {
        UIKitViewController(
            factory = mapViewController.invoke(selectedLocality, localityList, onItemClicked),
            modifier = Modifier.size(width = getScreenWidth(), height = percent70Height.dp),
        )
    }
}
