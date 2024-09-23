package enter_api_key_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import com.areeb.weatherunion.logic.api_credential_screen.SaveWeatherUnionApiKey
import com.areeb.weatherunion.logic.api_credential_screen.ShowSnackBar
import com.areeb.weatherunion.logic.api_credential_screen.UpdateWeatherUnionApiKey
import enter_api_key_screen.components.EnterApiKeyScreenTopAppBar
import enter_api_key_screen.components.EnterApiKeyTextField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.enter_weather_union_api_key
import weatherunionkmm.composeapp.generated.resources.press_keyboard_done_button_to_save_api_key
import weatherunionkmm.composeapp.generated.resources.weather_union_api_key

@Composable
fun EnterApiKeyScreen(
    viewModel: ApiCredentialScreenViewModel,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    val state by viewModel.state.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current,
        context = Dispatchers.Main.immediate,
    )
    LaunchedEffect(viewModel.event) {
        viewModel.event.collectLatest {
            when(it) {
                is ShowSnackBar -> {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(it.message)
                    }
                }
            }
        }
    }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var weatherUnionApiKeyVisibility by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { EnterApiKeyScreenTopAppBar(onBackPressed = onBackPressed) },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
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
                    value = state.weatherUnionApiKey,
                    onValueChange = {
                        viewModel.dispatch(UpdateWeatherUnionApiKey(apiKey = it))
                    },
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
                        viewModel.dispatch(SaveWeatherUnionApiKey)
                    },
                )
                Text(
                    stringResource(Res.string.press_keyboard_done_button_to_save_api_key),
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}
