import FeeRateKitKmm
import SwiftUI

class ContentViewModel: ObservableObject {
    @Published var text = "Loading..."

    private let feeRateKit = FeeRateKit(databaseDriverFactory: DatabaseDriverFactory())

    init() {
        Task {
            let text: String
            do {
                let coins = try await feeRateKit.getTopCoins(forceReload: false)

                text = coins.map { "\($0.uid) - \($0.name) - \($0.price.toStringExpanded())" }.joined(separator: "\n")
            } catch {
                text = error.localizedDescription
            }

            DispatchQueue.main.async {
                self.text = text
            }
        }
    }
}

struct ContentView: View {
    @StateObject private var viewModel = ContentViewModel()

    var body: some View {
        Text(viewModel.text)
    }
}

// struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
// }
