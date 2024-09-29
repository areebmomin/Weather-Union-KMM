package home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.areeb.weatherunion.data.locality_data.model.LocalityData
import org.jetbrains.compose.resources.stringResource
import utils.Colors
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.area
import weatherunionkmm.composeapp.generated.resources.city

@Composable
fun LocalityDropdownSection(
    modifier: Modifier = Modifier,
    locality: LocalityData,
    onCityTextFieldClicked: () -> Unit,
    onAreaTextFieldClicked: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 8.dp, top = 40.dp, bottom = 40.dp)
                .background(Color.Transparent)
                .clickable {
                    onCityTextFieldClicked()
                }
        ) {
            OutlinedTextField(
                label = {
                    Text(
                        stringResource(Res.string.city),
                        fontWeight = FontWeight.Medium,
                    )
                },
                value = locality.cityName,
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        stringResource(Res.string.city),
                        tint = Colors.WHITE_70_ALPHA,
                    )
                },
                onValueChange = { },
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = Color.White,
                    backgroundColor = Colors.BLACK_40_ALPHA,
                    disabledBorderColor = Color(0x4DEBEBF5),
                    disabledLabelColor = Colors.WHITE_80_ALPHA,
                ),
                singleLine = true,
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 8.dp, top = 40.dp, bottom = 40.dp)
                .background(Color.Transparent)
                .clickable {
                    onAreaTextFieldClicked()
                }
        ) {
            OutlinedTextField(
                label = {
                    Text(
                        stringResource(Res.string.area),
                        fontWeight = FontWeight.Medium,
                    )
                },
                value = locality.localityName,
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        stringResource(Res.string.area),
                        tint = Colors.WHITE_70_ALPHA,
                    )
                },
                onValueChange = { },
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = Color.White,
                    backgroundColor = Colors.BLACK_40_ALPHA,
                    disabledBorderColor = Color(0x4DEBEBF5),
                    disabledLabelColor = Colors.WHITE_80_ALPHA,
                ),
                singleLine = true,
            )
        }
    }
}
