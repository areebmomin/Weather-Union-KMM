import org.jetbrains.compose.resources.StringResource
import weatherunionkmm.composeapp.generated.resources.Res
import weatherunionkmm.composeapp.generated.resources.enter_api_key_screen_nav_name
import weatherunionkmm.composeapp.generated.resources.home_screen_nav_name
import weatherunionkmm.composeapp.generated.resources.info_screen_nav_name
import weatherunionkmm.composeapp.generated.resources.location_data_screen_nav_name

enum class Screen(val title: StringResource) {
    Home(title = Res.string.home_screen_nav_name),
    LocationData(title = Res.string.location_data_screen_nav_name),
    EnterApiKey(title = Res.string.enter_api_key_screen_nav_name),
    Info(title = Res.string.info_screen_nav_name),
}
