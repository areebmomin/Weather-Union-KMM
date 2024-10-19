# Weather Union KMM

## Concept:
- Multiplatform mobile app that displays live weather data using [Zomato](https://www.zomato.com/)'s free [Weather Union](https://www.weatherunion.com/) API. Which also has map to show weather updates of all supported locations.

## UI:
![Weather Union KMM UI Recording](https://github.com/areebmomin/Weather-Union-KMM/blob/main/assets/Weather%20Union%20Recording.gif)

## Tech:
- 

## Module Graph

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
