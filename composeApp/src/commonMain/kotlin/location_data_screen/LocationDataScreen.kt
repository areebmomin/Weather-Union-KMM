package location_data_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.areeb.weatherunion.logic.location_data_screen.Error
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel
import com.areeb.weatherunion.logic.location_data_screen.OnLocalitySelected
import dev.jordond.connectivity.Connectivity
import dev.jordond.connectivity.compose.rememberConnectivityState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import location_data_screen.components.MapComponent
import location_data_screen.components.WeatherDetailsSection
import org.jetbrains.compose.resources.stringResource
import utils.Colors
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
    val connectivityState = rememberConnectivityState { autoStart = true }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    when (connectivityState.status) {
        is Connectivity.Status.Disconnected -> {
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = "Network Disconnected",
                    duration = SnackbarDuration.Short,
                )
            }
        }

        else -> {}
    }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collectLatest {
            when (it) {
                is Error -> {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = it.message,
                            duration = SnackbarDuration.Long,
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BASE_SCREEN_BACKGROUND)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {
        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                MapComponent(
                    localityList = state.localityList,
                    selectedLocality = state.selectedLocality,
                    onItemClicked = {
                        viewModel.dispatch(OnLocalitySelected(locality = it))
                    },
                )
                WeatherDetailsSection(
                    modifier = Modifier.weight(1f),
                    weatherData = state.weatherData,
                    isLoading = state.isLoading,
                    locality = state.selectedLocality,
                )
            }
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp)
                    .background(Colors.WHITE_90_ALPHA, CircleShape)
                    .size(40.dp),
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
