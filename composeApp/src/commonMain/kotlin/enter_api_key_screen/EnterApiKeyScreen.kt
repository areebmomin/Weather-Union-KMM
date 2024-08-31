package enter_api_key_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import enter_api_key_screen.components.EnterApiKeyScreenTopAppBar
import enter_api_key_screen.components.EnterApiKeyTextField
import org.jetbrains.compose.resources.stringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.enter_map_api_key
import weatherunionkmm.composeapp.generated.resources.enter_weather_union_api_key
import weatherunionkmm.composeapp.generated.resources.map_api_key
import weatherunionkmm.composeapp.generated.resources.weather_union_api_key

@Composable
fun EnterApiKeyScreen(
    viewModel: ApiCredentialScreenViewModel,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var weatherUnionApiKey by remember { mutableStateOf("") }
    var weatherUnionApiKeyVisibility by remember { mutableStateOf(false) }
    var mapApiKey by remember { mutableStateOf("") }
    var mapApiKeyVisibility by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { EnterApiKeyScreenTopAppBar(onBackPressed = onBackPressed) }
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF2E335A),
                            Color(0xFF1C1B33),
                        ),
                        tileMode = TileMode.Clamp,
                    ),
                )
                .fillMaxSize(),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .focusRequester(focusRequester)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        focusManager.clearFocus()
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                EnterApiKeyTextField(
                    value = weatherUnionApiKey,
                    onValueChange = { weatherUnionApiKey = it },
                    label = { Text(stringResource(Res.string.weather_union_api_key)) },
                    placeholder = {
                        Text(stringResource(Res.string.enter_weather_union_api_key))
                    },
                    isContentVisible = weatherUnionApiKeyVisibility,
                    onVisibilityIconClicked = {
                        weatherUnionApiKeyVisibility = !weatherUnionApiKeyVisibility
                    },
                    onDone = {
                        focusManager.clearFocus()
                    },
                )
                EnterApiKeyTextField(
                    value = mapApiKey,
                    onValueChange = { mapApiKey = it },
                    label = { Text(stringResource(Res.string.map_api_key)) },
                    placeholder = {
                        Text(stringResource(Res.string.enter_map_api_key))
                    },
                    isContentVisible = mapApiKeyVisibility,
                    onVisibilityIconClicked = {
                        mapApiKeyVisibility = !mapApiKeyVisibility
                    },
                    onDone = {
                        focusManager.clearFocus()
                    },
                )
            }
        }
    }
}
