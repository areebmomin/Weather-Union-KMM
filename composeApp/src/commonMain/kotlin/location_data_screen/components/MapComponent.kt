package location_data_screen.components

import androidx.compose.runtime.Composable
import com.areeb.weatherunion.data.locality_data.model.LocalityData

@Composable
expect fun MapComponent(localityList: List<LocalityData>, onItemClicked: (LocalityData) -> Unit)
