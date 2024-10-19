# <img src="https://github.com/areebmomin/Weather-Union-KMM/blob/main/iosApp/iosApp/Assets.xcassets/AppIcon.appiconset/app_logo.png" width="32" height="32" alt="App Logo"> Weather Union KMM

## Concept:
- Multiplatform mobile app that displays live weather data using [Zomato](https://www.zomato.com/)'s free [Weather Union](https://www.weatherunion.com/) API. Which also has map to show weather updates of all supported locations.

## UI:
![Weather Union KMM UI Recording](https://github.com/areebmomin/Weather-Union-KMM/blob/main/assets/Weather%20Union%20Recording.gif)

## Tech:
- **UI** - [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/)
- **Logic** - [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- **Viewmodel** - [Android ViewModel](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html)
- **DI** - [Kotlin Inject](https://github.com/evant/kotlin-inject)
- **Database** - [SQLDelight](https://sqldelight.github.io/sqldelight/2.0.2/multiplatform_sqlite/)
- **Key-Value Store** - [Proto DataStore](https://medium.com/@aribmomin111/unlocking-proto-datastore-magic-in-kmm-d397f40a0805)
- **Map** - [Google Map](https://medium.com/@aribmomin111/google-maps-in-compose-multiplatform-4db4badffb6f)
- **Logic Test** - [Kotlin Test](https://kotlinlang.org/api/latest/kotlin.test/)
- **UI Test** - [Compose Multiplatform Test](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html)

## Module Graph:

```mermaid
%%{
  init: {
    'theme': 'forest'
  }
}%%

graph LR
  subgraph :modules
    :modules:shared["shared"]
    :modules:core["core"]
    :modules:data["data"]
    :modules:logic["logic"]
    :modules:testing["testing"]
  end
  :composeApp --> :modules:shared
  :modules:shared --> :modules:core
  :modules:shared --> :modules:data
  :modules:shared --> :modules:logic
  :modules:logic --> :modules:core
  :modules:logic --> :modules:data
  :modules:testing --> :modules:core
  :modules:testing --> :modules:data
  :modules:testing --> :modules:logic
  :modules:data --> :modules:core
```
## Add Your API Key:

### For Google Maps:
1) Generate Google Maps API key - [Doc](https://developers.google.com/maps/get-started#api-key)
2) For Android, put your API key in the [***secrets.properties***](https://github.com/areebmomin/Weather-Union-KMM/blob/main/secrets.properties) file.
3) For iOS, put your API key in the [***iOSApp.swift***](https://github.com/areebmomin/Weather-Union-KMM/blob/main/iosApp/iosApp/iOSApp.swift) file.

### For Weather Union:
1) Login to [Weather Union](https://www.weatherunion.com/) and generate an API Key.
2) After launching the app, goto Menu icon (Top Right corner) -> 'Enter API Key' menu -> Put your API Key in TextField -> Click 'Done' on the Keyboard.
 
