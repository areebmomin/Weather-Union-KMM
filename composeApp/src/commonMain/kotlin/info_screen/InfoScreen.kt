package info_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import info_screen.components.InfoScreenTopApoBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import utils.Colors
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.about_colon
import weatherunionkmm.composeapp.generated.resources.about_value
import weatherunionkmm.composeapp.generated.resources.app_name
import weatherunionkmm.composeapp.generated.resources.github_icon
import weatherunionkmm.composeapp.generated.resources.ic_github
import weatherunionkmm.composeapp.generated.resources.ic_linkedin
import weatherunionkmm.composeapp.generated.resources.ic_weather_union
import weatherunionkmm.composeapp.generated.resources.linkedin_icon
import weatherunionkmm.composeapp.generated.resources.project_name_colon
import weatherunionkmm.composeapp.generated.resources.version_colon
import weatherunionkmm.composeapp.generated.resources.version_string
import weatherunionkmm.composeapp.generated.resources.weather_union_icon

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    uriHandler: UriHandler = LocalUriHandler.current,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BASE_SCREEN_BACKGROUND)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        topBar = { InfoScreenTopApoBar(onBackPressed = onBackPressed) }
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            Colors.BACKGROUND_GRADIENT_1,
                            Colors.BACKGROUND_GRADIENT_2,
                        ),
                        tileMode = TileMode.Clamp,
                    ),
                )
                .fillMaxSize(),
        ) {
            Column(
                modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                Row(
                    modifier = modifier.padding(vertical = 8.dp),
                ) {
                    Text(
                        stringResource(Res.string.project_name_colon),
                        color = Color(0x99EBEBF5),
                    )
                    Text(
                        stringResource(Res.string.app_name),
                        color = Color(0xE6FFFFFF),
                    )
                }
                Row(
                    modifier = modifier.padding(vertical = 8.dp),
                ) {
                    Text(
                        stringResource(Res.string.version_colon),
                        color = Color(0x99EBEBF5),
                    )
                    Text(
                        stringResource(Res.string.version_string),
                        color = Color(0xE6FFFFFF),
                    )
                }
                Row(
                    modifier = modifier.padding(vertical = 8.dp),
                ) {
                    Text(
                        stringResource(Res.string.about_colon),
                        color = Color(0x99EBEBF5),
                    )
                    Text(
                        stringResource(Res.string.about_value),
                        color = Color(0xE6FFFFFF),
                    )
                }
                Row(
                    modifier = modifier.padding(vertical = 24.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Image(
                        modifier = modifier
                            .size(48.dp)
                            .clickable {
                                uriHandler.openUri("https://www.weatherunion.com")
                            },
                        painter = painterResource(Res.drawable.ic_weather_union),
                        contentDescription = stringResource(Res.string.weather_union_icon),
                        contentScale = ContentScale.FillBounds
                    )
                    Image(
                        modifier = modifier
                            .size(48.dp)
                            .clickable {
                                uriHandler.openUri("https://github.com/areebmomin/Weather-Union-KMM")
                            },
                        painter = painterResource(Res.drawable.ic_github),
                        contentDescription = stringResource(Res.string.github_icon),
                        contentScale = ContentScale.FillBounds
                    )
                    Image(
                        modifier = modifier
                            .size(48.dp)
                            .clickable {
                                uriHandler.openUri("https://www.linkedin.com/in/areeb-momin")
                            },
                        painter = painterResource(Res.drawable.ic_linkedin),
                        contentDescription = stringResource(Res.string.linkedin_icon),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}
