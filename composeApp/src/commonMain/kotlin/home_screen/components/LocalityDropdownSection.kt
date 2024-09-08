package home_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import org.jetbrains.compose.resources.stringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.area
import weatherunionkmm.composeapp.generated.resources.city

@Composable
fun LocalityDropdownSection(modifier: Modifier = Modifier, localityList: List<LocalityData>) {
    Row {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                label = {
                    Text(
                        stringResource(Res.string.city),
                        fontWeight = FontWeight.Bold,
                    )
                },
                value = localityList.firstOrNull()?.cityName ?: "",
                enabled = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp, top = 40.dp, bottom = 40.dp),
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        stringResource(Res.string.city),
                        tint = Color(0xB3FFFFFF),
                    )
                },
                onValueChange = { },
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color(0xFFFFFFFF),
                    backgroundColor = Color(0x66000000),
                    focusedBorderColor = Color(0x4DEBEBF5),
                    unfocusedBorderColor = Color(0x4DEBEBF5),
                    focusedLabelColor = Color(0xCCFFFFFF),
                    unfocusedLabelColor = Color(0xCCFFFFFF),
                ),
                maxLines = 1,
            )
        }
        Box(
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                label = {
                    Text(
                        stringResource(Res.string.area),
                        fontWeight = FontWeight.Bold,
                    )
                },
                value = localityList.firstOrNull()?.localityName ?: "",
                enabled = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp, top = 40.dp, bottom = 40.dp),
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        stringResource(Res.string.area),
                        tint = Color(0xB3FFFFFF),
                    )
                },
                onValueChange = { },
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color(0xFFFFFFFF),
                    backgroundColor = Color(0x66000000),
                    focusedBorderColor = Color(0x4DEBEBF5),
                    unfocusedBorderColor = Color(0x4DEBEBF5),
                    focusedLabelColor = Color(0xCCFFFFFF),
                    unfocusedLabelColor = Color(0xCCFFFFFF),
                ),
                maxLines = 1,
            )
        }
    }
}
