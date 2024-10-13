package compose.enter_api_key_screen

import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import com.areeb.weatherunion.core.coroutines.AppCoroutineDispatchers
import com.areeb.weatherunion.data.repository.api_credential.ApiCredentialRepositoryImpl
import com.areeb.weatherunion.logic.api_credential_screen.ApiCredentialScreenViewModel
import core.logger.TestLogger
import data.dao.FakeApiKeyDaoImpl
import enter_api_key_screen.EnterApiKeyScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class EnterApiKeyScreenTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testEnterApiKeyAndSave() = runComposeUiTest {
        val viewModel = ApiCredentialScreenViewModel(
            coreLogger = TestLogger(),
            coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
            apiCredentialRepository = ApiCredentialRepositoryImpl(
                apiKeyDao = FakeApiKeyDaoImpl(),
            )
        )

        // Set the content with the EnterApiKeyScreen composable
        setContent {
            EnterApiKeyScreen(
                viewModel = viewModel,
                coroutineDispatchers = AppCoroutineDispatchers.coroutineDispatchers,
                onBackPressed = {}
            )
        }

        // Interact with the TextField
        val testApiKey = "test-api-key"
        onNodeWithTag("weather_union_api_key_text_field").performTextInput(testApiKey)

        // Use SnapshotFlow to await the ViewModel state update
        runBlocking {
            snapshotFlow { viewModel.state.value.weatherUnionApiKey }
                .first { it == testApiKey }
        }

        // Simulate pressing the IME action button to save the API key
        onNodeWithTag("weather_union_api_key_text_field").performImeAction()

        // Assert the confirmation message after the key is saved
        onNodeWithText("Weather Union API Key saved successfully").assertIsDisplayed()
    }
}
