import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    private let applicationComponent = ApplicationComponent.companion.create()
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(logicComponent: applicationComponent, mapUIViewController: { () -> UIViewController in
            let swiftUIView = VStack {
                Text("SwiftUI in Compose Multiplatform")
            }
            return UIHostingController(rootView: swiftUIView)
        })
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



