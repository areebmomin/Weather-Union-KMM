package enter_api_key_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import utils.Colors
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.hide_password
import weatherunionkmm.composeapp.generated.resources.show_password

@Composable
fun EnterApiKeyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    placeholder: @Composable () -> Unit,
    isContentVisible: Boolean,
    onVisibilityIconClicked: () -> Unit,
    onDone: KeyboardActionScope.() -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = Modifier
            .padding(16.dp)
            .width(TextFieldDefaults.MinWidth),
        placeholder = placeholder,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            imeAction = ImeAction.Done,
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            backgroundColor = Color(0xFF1C1B33),
            cursorColor = Color(0xCCFFFFFF),
            focusedBorderColor = Colors.WHITE_70_ALPHA,
            unfocusedBorderColor = Colors.WHITE_40_ALPHA,
            focusedLabelColor = Colors.WHITE_70_ALPHA,
            unfocusedLabelColor = Colors.WHITE_40_ALPHA,
            placeholderColor = Color(0x4DFFFFFF),
        ),
        visualTransformation = if (isContentVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation('\u002A')
        },
        trailingIcon = {
            val icon = if (isContentVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (isContentVisible) {
                stringResource(Res.string.hide_password)
            } else {
                stringResource(Res.string.show_password)
            }

            IconButton(
                onClick = onVisibilityIconClicked,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = description,
                    tint = Colors.WHITE_40_ALPHA,
                )
            }
        },
        keyboardActions = KeyboardActions(
            onDone = onDone,
        ),
    )
}
