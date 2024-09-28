package info_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import utils.Colors
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.info
import weatherunionkmm.composeapp.generated.resources.navigate_to_home_page

@Composable
fun InfoScreenTopApoBar(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    TopAppBar(
        backgroundColor = Colors.TOP_APP_BAR_BACKGROUND,
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
                stringResource(Res.string.info),
                color = Color.White,
                fontSize = 20.sp,
                modifier = modifier.padding(start = 20.dp),
            )
        }
    }
}
