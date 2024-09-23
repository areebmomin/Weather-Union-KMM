package location_data_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel
import com.areeb.weatherunion.logic.location_data_screen.OnLocalitySelected
import kotlinx.coroutines.Dispatchers
import location_data_screen.components.MapComponent
import location_data_screen.components.WeatherDetailsSection
import org.jetbrains.compose.resources.stringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.navigate_to_home_page

@Composable
fun LocationDataScreen(
    viewModel: LocationDataScreenViewModel,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current,
        context = Dispatchers.Main.immediate,
    )

    Scaffold {
        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                MapComponent(
                    localityList = state.localityList,
                    onItemClicked = {
                        viewModel.dispatch(OnLocalitySelected(locality = it))
                    },
                )
                WeatherDetailsSection(
                    modifier = Modifier.weight(1f),
                    weatherData = state.weatherData,
                    locality = state.selectedLocality,
                )
            }
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier.padding(start = 4.dp, top = 8.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.navigate_to_home_page),
                    tint = Color.Black,
                )
            }
        }
    }
}
