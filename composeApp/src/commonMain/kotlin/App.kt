import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import com.areeb.weatherunion.logic.api_credential_screen.UpdateWeatherUnionApiKey
import com.areeb.weatherunion.logic.di.LogicComponent
import com.areeb.weatherunion.logic.home_screen.HomeScreenViewModel
import com.areeb.weatherunion.logic.home_screen.OnCitySelected
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(
    logicComponent: LogicComponent,
) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val viewModel: HomeScreenViewModel = remember {
            ViewModelProvider.create(
                ViewModelStore(),
                logicComponent.homeScreenViewModelFactory
            )[HomeScreenViewModel::class]
        }

        val apiCredentialScreenViewModel: ApiCredentialScreenViewModel = remember {
            ViewModelProvider.create(
                ViewModelStore(),
                logicComponent.apiCredentialScreenViewModelFactory
            )[ApiCredentialScreenViewModel::class]
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                showContent = !showContent
                apiCredentialScreenViewModel.dispatch(UpdateWeatherUnionApiKey("123456"))
                viewModel.dispatch(OnCitySelected(localityId = "ZWL002059"))
            }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}