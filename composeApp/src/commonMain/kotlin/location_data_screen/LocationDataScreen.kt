package location_data_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel
import location_data_screen.components.MapComponent

@Composable
fun LocationDataScreen(
    viewModel: LocationDataScreenViewModel,
    modifier: Modifier = Modifier,
) {
    Scaffold {
        Column {
            MapComponent()
        }
    }
}
