package home_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.ic_menu_option

@Composable
fun HomeScreenAppBar(
    onLocationDataMenuClicked: () -> Unit,
    onEnterApiKeyMenuClicked: () -> Unit,
    onInfoMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showMenu: Boolean by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(end = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Spacer(modifier = modifier.weight(1f))
        Box {
            IconButton(
                onClick = { showMenu = true },
            ) {
                Icon(
                    painter = painterResource(resource = Res.drawable.ic_menu_option),
                    contentDescription = "Menu",
                    tint = Color.White,
                )
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        showMenu = false
                        onLocationDataMenuClicked()
                    },
                ) {
                    Text("Location Data")
                }
                DropdownMenuItem(
                    onClick = {
                        showMenu = false
                        onEnterApiKeyMenuClicked()
                    },
                ) {
                    Text("Enter API Key")
                }
                DropdownMenuItem(
                    onClick = {
                        showMenu = false
                        onInfoMenuClicked()
                    },
                ) {
                    Text("Info")
                }
            }
        }
    }
}
