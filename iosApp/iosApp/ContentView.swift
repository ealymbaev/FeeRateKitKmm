import FeeRateKitKmm
import SwiftUI

class ContentViewModel: ObservableObject {
    @Published var text = "Loading..."

    init() {
        Task {
            let text: String
            do {
                let launches = try await FeeRateKit().getLaunches()

                text = launches.map { $0.missionName }.joined(separator: "\n")
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
