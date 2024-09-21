import SwiftUI
import GoogleMaps

@main
struct iOSApp: App {
    init() {
            // Provide the Google Maps API key
            GMSServices.provideAPIKey("YOUR_API_KEY") // Replace with your actual API key
        }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
