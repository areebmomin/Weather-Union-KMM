package location_data_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import utils.getScreenHeight

@Composable
actual fun MapComponent(localityList: List<LocalityData>, onItemClicked: (LocalityData) -> Unit) {
    val percent70Height = getScreenHeight().value * 0.7
    Box(
        modifier = Modifier
            .height(percent70Height.dp)
            .fillMaxWidth(),
    ) {
        val bandra = LatLng(19.068857, 72.833)
        val bandraMarkerState = rememberMarkerState(position = bandra)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(bandra, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = bandraMarkerState,
                title = "Bandra West",
                snippet = "Marker in Bandra",
                onClick = {
                    onItemClicked(localityList[0])
                    true
                }
            )
        }
    }
}
