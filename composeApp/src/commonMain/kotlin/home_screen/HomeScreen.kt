package home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.areeb.weatherunion.logic.home_screen.viewmodel.HomeScreenViewModel
import com.areeb.weatherunion.logic.home_screen.viewmodel.OnLocalitySelected
import com.areeb.weatherunion.logic.home_screen.viewmodel.RefreshWeatherData
import home_screen.components.DeviceDescriptionText
import home_screen.components.HomeScreenAppBar
import home_screen.components.HumidityTile
import home_screen.components.LocalityDropdownSection
import home_screen.components.RainAccumulationTile
import home_screen.components.RainIntensityTile
import home_screen.components.TemperatureText
import home_screen.components.WindDirectionTile
import home_screen.components.WindSpeedTile
import home_screen.components.showLocalitiesBottomSheet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.background_image
import weatherunionkmm.composeapp.generated.resources.ic_home_screen_background
import weatherunionkmm.composeapp.generated.resources.select_area
import weatherunionkmm.composeapp.generated.resources.select_city

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onLocationDataMenuClicked: () -> Unit,
    onEnterApiKeyMenuClicked: () -> Unit,
    onInfoMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current,
        context = Dispatchers.Main.immediate,
    )

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()
    var showCityBottomSheet by remember { mutableStateOf(false) }
    var showAreaBottomSheet by remember { mutableStateOf(false) }

    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(Res.drawable.ic_home_screen_background),
                contentDescription = stringResource(Res.string.background_image),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            ) {
                HomeScreenAppBar(
                    onRefreshClicked = {
                        viewModel.dispatch(RefreshWeatherData)
                    },
                    onLocationDataMenuClicked = onLocationDataMenuClicked,
                    onEnterApiKeyMenuClicked = onEnterApiKeyMenuClicked,
                    onInfoMenuClicked = onInfoMenuClicked,
                )
                TemperatureText(temperatureData = state.weatherData.temperature)
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth().padding(vertical = 10.dp),
                ) {
                    Spacer(modifier = modifier.weight(1f))
                    HumidityTile(
                        modifier = modifier.weight(2f),
                        humidityData = state.weatherData.humidity,
                    )
                    Spacer(modifier = modifier.weight(1f))
                }
                Row(
                    modifier = modifier.fillMaxWidth().padding(vertical = 10.dp),
                ) {
                    WindSpeedTile(
                        modifier = modifier.weight(1f),
                        windSpeedData = state.weatherData.windSpeed,
                    )
                    WindDirectionTile(
                        modifier = modifier.weight(1f),
                        windDirectionData = state.weatherData.windDirection,
                    )
                }
                Row(
                    modifier = modifier.fillMaxWidth().padding(vertical = 10.dp),
                ) {
                    RainIntensityTile(
                        modifier = modifier.weight(1f),
                        rainIntensityData = state.weatherData.rainIntensity,
                    )
                    RainAccumulationTile(
                        modifier = modifier.weight(1f),
                        rainAccumulationData = state.weatherData.rainAccumulation,
                    )
                }
                DeviceDescriptionText(deviceDescription = state.weatherData.deviceDescription)
                Spacer(modifier = modifier.weight(1f))
                LocalityDropdownSection(
                    locality = state.selectedLocality,
                    onCityTextFieldClicked = {
                        showCityBottomSheet = true
                    },
                    onAreaTextFieldClicked = {
                        showAreaBottomSheet = true
                    },
                )
            }
        }

        if (showCityBottomSheet) {
            showLocalitiesBottomSheet(
                title = stringResource(Res.string.select_city),
                sheetState = sheetState,
                localityList = state.uniqueCitiesFirstLocalityList,
                selectedItemDisplayName = state.selectedLocality.cityName,
                onDismissRequest = {
                    showCityBottomSheet = false
                },
                itemDisplayName = {
                    cityName
                },
                onItemClicked = {
                    viewModel.dispatch(OnLocalitySelected(locality = it))
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showCityBottomSheet = false
                        }
                    }
                },
            )
        }

        if (showAreaBottomSheet) {
            showLocalitiesBottomSheet(
                title = stringResource(Res.string.select_area),
                sheetState = sheetState,
                localityList = state.localitiesMap[state.selectedLocality.cityName] ?: emptyList(),
                selectedItemDisplayName = state.selectedLocality.localityName,
                onDismissRequest = {
                    showAreaBottomSheet = false
                },
                itemDisplayName = {
                    localityName
                },
                onItemClicked = {
                    viewModel.dispatch(OnLocalitySelected(locality = it))
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showAreaBottomSheet = false
                        }
                    }
                },
            )
        }
    }
}
