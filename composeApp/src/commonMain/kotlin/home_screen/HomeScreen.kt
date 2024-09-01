package home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.areeb.weatherunion.logic.home_screen.viewmodel.HomeScreenViewModel
import home_screen.components.HomeScreenAppBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.background_image
import weatherunionkmm.composeapp.generated.resources.ic_home_screen_background

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onLocationDataMenuClicked: () -> Unit,
    onEnterApiKeyMenuClicked: () -> Unit,
    onInfoMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                modifier = modifier.fillMaxSize(),
            ) {
                HomeScreenAppBar(
                    onLocationDataMenuClicked = onLocationDataMenuClicked,
                    onEnterApiKeyMenuClicked = onEnterApiKeyMenuClicked,
                    onInfoMenuClicked = onInfoMenuClicked,
                )
            }
        }
    }
}
