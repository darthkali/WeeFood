import SwiftUI
import shared


@main
struct iOSApp: App {
    
    private let networkModule = NetworkModule()
    private let cacheModule = DatabaseModule()
    
    var body: some Scene {
        WindowGroup {
            
            BottomBar()
        }
    }
}
