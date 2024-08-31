package home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.areeb.weatherunion.logic.home_screen.HomeScreenViewModel
import home_screen.components.HomeScreenAppBar
import org.jetbrains.compose.resources.painterResource
import weatherunionkmm.composeapp.generated.resources.Res
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
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(Res.drawable.ic_home_screen_background),
                contentDescription = "Background Image",
                contentScale = ContentScale.FillBounds
            )
            HomeScreenAppBar(
                onLocationDataMenuClicked = onLocationDataMenuClicked,
                onEnterApiKeyMenuClicked = onEnterApiKeyMenuClicked,
                onInfoMenuClicked = onInfoMenuClicked,
            )
        }
    }
}
