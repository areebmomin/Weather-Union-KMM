package location_data_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import utils.getScreenHeight

@Composable
actual fun MapComponent(
    localityList: List<LocalityData>,
    selectedLocality: LocalityData,
    onItemClicked: (LocalityData) -> Unit,
) {
    val percent70Height = getScreenHeight().value * 0.7
    var isMapLoaded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .height(percent70Height.dp)
            .fillMaxWidth(),
    ) {
        val selectedLocalityCoordinates =
            LatLng(selectedLocality.latitude, selectedLocality.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(selectedLocalityCoordinates, 12f)
        }
        val markerStates = remember(localityList) {
            localityList.associate { locality ->
                locality.localityId to MarkerState(
                    position = LatLng(locality.latitude, locality.longitude)
                )
            }
        }

        LaunchedEffect(isMapLoaded, selectedLocalityCoordinates) {
            if (isMapLoaded) {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(
                        selectedLocalityCoordinates,
                        12f,
                    )
                )
            }
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            }
        ) {
            if (isMapLoaded) {
                localityList.forEach { locality ->
                    val markerState = markerStates[locality.localityId]!!
                    Marker(
                        state = markerState,
                        title = locality.localityName,
                        snippet = locality.cityName,
                        onClick = {
                            onItemClicked(locality)
                            false
                        }
                    )

                    if (locality == selectedLocality) {
                        LaunchedEffect(markerState) {
                            markerState.showInfoWindow()
                        }
                    }
                }
            }
        }
    }
}
