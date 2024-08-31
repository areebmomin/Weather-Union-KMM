import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import com.areeb.weatherunion.logic.di.LogicComponent
import com.areeb.weatherunion.logic.home_screen.HomeScreenViewModel
import com.areeb.weatherunion.logic.location_data_screen.LocationDataScreenViewModel
import enter_api_key_screen.EnterApiKeyScreen
import home_screen.HomeScreen
import info_screen.InfoScreen
import location_data_screen.LocationDataScreen

@Composable
fun App(
    logicComponent: LogicComponent,
    navController: NavHostController = rememberNavController(),
) {
    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.name,
        ) {
            composable(route = Screen.Home.name) {
                val viewModel: HomeScreenViewModel = remember {
                    ViewModelProvider.create(
                        ViewModelStore(),
                        logicComponent.homeScreenViewModelFactory
                    )[HomeScreenViewModel::class]
                }

                HomeScreen(
                    viewModel = viewModel,
                    onLocationDataMenuClicked = {
                        navController.navigate(Screen.LocationData.name)
                    },
                    onEnterApiKeyMenuClicked = {
                        navController.navigate(Screen.EnterApiKey.name)
                    },
                    onInfoMenuClicked = {
                        navController.navigate(Screen.Info.name)
                    },
                )
            }
            composable(route = Screen.LocationData.name) {
                val viewModel: LocationDataScreenViewModel = remember {
                    ViewModelProvider.create(
                        ViewModelStore(),
                        logicComponent.locationDataScreenViewModelFactory
                    )[LocationDataScreenViewModel::class]
                }

                LocationDataScreen(
                    viewModel = viewModel,
                )
            }
            composable(route = Screen.EnterApiKey.name) {
                val viewModel: ApiCredentialScreenViewModel = remember {
                    ViewModelProvider.create(
                        ViewModelStore(),
                        logicComponent.apiCredentialScreenViewModelFactory
                    )[ApiCredentialScreenViewModel::class]
                }

                EnterApiKeyScreen(
                    viewModel = viewModel,
                )
            }
            composable(route = Screen.Info.name) {
                InfoScreen()
            }
        }
    }
}
