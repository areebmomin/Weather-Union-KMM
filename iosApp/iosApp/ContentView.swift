import UIKit
import SwiftUI
import ComposeApp
import GoogleMaps

struct GoogleMapView: UIViewRepresentable {
    let selectedLocality: DataLocalityData
    let localityList: [DataLocalityData]
    let onItemClick: (DataLocalityData) -> KotlinUnit
    
    func makeUIView(context: Context) -> GMSMapView {
        let options = GMSMapViewOptions()
        options.camera = GMSCameraPosition.camera(withLatitude: selectedLocality.latitude, longitude: selectedLocality.longitude, zoom: 12.0)

        let mapView = GMSMapView(options: options)
        
        mapView.delegate = context.coordinator

        return mapView
    }

    func updateUIView(_ uiView: GMSMapView, context: Context) {
        // Update camera position when selected locality changes
                let cameraPosition = GMSCameraPosition.camera(
                    withLatitude: selectedLocality.latitude,
                    longitude: selectedLocality.longitude,
                    zoom: 12.0
                )
                
                uiView.animate(to: cameraPosition) // Animate to new position
        
                uiView.clear()
        
                // Optionally update markers if the locality list changes
                for locality in localityList {
                    let marker = GMSMarker()
                    marker.position = CLLocationCoordinate2D(latitude: locality.latitude, longitude: locality.longitude)
                    marker.title = locality.localityName
                    marker.snippet = locality.cityName
                    marker.userData = locality
                    marker.map = uiView
                    
                    if (locality.localityId == selectedLocality.localityId) {
                        uiView.selectedMarker = marker
                    }
                }
    }
    
    func makeCoordinator() -> Coordinator {
            Coordinator(self)
        }

        class Coordinator: NSObject, GMSMapViewDelegate {
            var parent: GoogleMapView

            init(_ parent: GoogleMapView) {
                self.parent = parent
            }

            func mapView(_ mapView: GMSMapView, didTap marker: GMSMarker) -> Bool {
                if let locality = marker.userData as? DataLocalityData {
                    var result = parent.onItemClick(locality)
                }
                return false
            }
        }
}


struct ComposeView: UIViewControllerRepresentable {
    private let applicationComponent = ApplicationComponent.companion.create()
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(logicComponent: applicationComponent, mapUIViewController: { selectedLocality, localityList, onItemClick in {
            UIHostingController(rootView: GoogleMapView(selectedLocality: selectedLocality, localityList: localityList, onItemClick: onItemClick))
            }
        })
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(edges: .all)
            .ignoresSafeArea(.keyboard)
    }
}
