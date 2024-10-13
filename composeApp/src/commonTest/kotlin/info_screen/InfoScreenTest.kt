package info_screen

import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertTrue

class InfoScreenTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testInfoScreen() = runComposeUiTest {
        // Set the content with the InfoScreen composable
        setContent {
            InfoScreen(onBackPressed = {})
        }

        // Test if the project name is displayed
        onNodeWithText("Project Name: ").assertExists()
        onNodeWithText("Weather Union KMM").assertExists()

        // Test if the version is displayed
        onNodeWithText("Version: ").assertExists()
        onNodeWithText("1.0").assertExists()

        // Test if the about section is displayed
        onNodeWithText("About: ").assertExists()

        // Test clickable images/icons with content descriptions
        onNodeWithContentDescription("Weather Union Icon").assertExists()
        onNodeWithContentDescription("GitHub Icon").assertExists()
        onNodeWithContentDescription("LinkedIn Icon").assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testInfoScreenButtonClicks() = runComposeUiTest {
        var backButtonClicked = false

        // Set content for testing with a mocked onBackPressed
        setContent {
            InfoScreen(onBackPressed = { backButtonClicked = true })
        }

        // Perform a click on the back button and check if the action is triggered
        onNodeWithContentDescription("Navigate to Home Page").performClick()
        assertTrue(backButtonClicked)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testUriHandlerInteractions() = runComposeUiTest {
        val uriHandler = object : UriHandler {
            var lastOpenedUri: String? = null

            override fun openUri(uri: String) {
                lastOpenedUri = uri
            }
        }

        // Set the content with a mocked UriHandler
        setContent {
            InfoScreen(onBackPressed = {}, uriHandler = uriHandler)
        }

        // Simulate clicks on the icons and verify URIs
        onNodeWithContentDescription("Weather Union Icon").performClick()
        assertTrue(uriHandler.lastOpenedUri == "https://www.weatherunion.com")

        onNodeWithContentDescription("GitHub Icon").performClick()
        assertTrue(uriHandler.lastOpenedUri == "https://github.com/areebmomin/Weather-Union-KMM")

        onNodeWithContentDescription("LinkedIn Icon").performClick()
        assertTrue(uriHandler.lastOpenedUri == "https://www.linkedin.com/in/areeb-momin")
    }
}
