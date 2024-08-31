package info_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.ic_github
import weatherunionkmm.composeapp.generated.resources.ic_linkedin
import weatherunionkmm.composeapp.generated.resources.ic_weather_union
import weatherunionkmm.composeapp.generated.resources.navigate_to_home_page

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    uriHandler: UriHandler = LocalUriHandler.current,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF2E335A),
                elevation = 16.dp,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.navigate_to_home_page),
                            tint = Color.White,
                        )
                    }
                    Text(
                        "Info",
                        color = Color.White,
                        fontSize = 22.sp,
                        modifier = modifier.padding(start = 20.dp),
                    )
                }
            }
        }
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
                modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                Row(
                    modifier = modifier.padding(vertical = 8.dp),
                ) {
                    Text(
                        "Project Name: ",
                        color = Color(0x99EBEBF5),
                    )
                    Text(
                        "Weather Union KMM",
                        color = Color(0xE6FFFFFF),
                    )
                }
                Row(
                    modifier = modifier.padding(vertical = 8.dp),
                ) {
                    Text(
                        "Version: ",
                        color = Color(0x99EBEBF5),
                    )
                    Text(
                        "1.0",
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
                        contentDescription = "Weather Union Icon",
                        contentScale = ContentScale.FillBounds
                    )
                    Image(
                        modifier = modifier
                            .size(48.dp)
                            .clickable {
                                uriHandler.openUri("https://github.com/areebmomin/Weather-Union-KMM")
                            },
                        painter = painterResource(Res.drawable.ic_github),
                        contentDescription = "GitHub Icon",
                        contentScale = ContentScale.FillBounds
                    )
                    Image(
                        modifier = modifier
                            .size(48.dp)
                            .clickable {
                                uriHandler.openUri("https://www.linkedin.com/in/areeb-momin")
                            },
                        painter = painterResource(Res.drawable.ic_linkedin),
                        contentDescription = "LinkedIn Icon",
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}
