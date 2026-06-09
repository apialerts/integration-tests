import APIAlerts
import Foundation

let apiKey = ProcessInfo.processInfo.environment["APIALERTS_API_KEY"] ?? ""
if apiKey.isEmpty {
    fputs("Error: APIALERTS_API_KEY is not set\n", stderr)
    exit(1)
}

// Bump alongside the package pin in Package.swift on release.
let version = "1.2.0"

APIAlerts.configure(apiKey)

let result = await APIAlerts.sendAsync(
    Event(
        message: "Published smoke - Swift \(version)",
        channel: "testing",
        event: "ci.sdk.smoke.swift",
        title: "Published Package OK",
        tags: ["smoke", "swift", "published"],
        link: "https://github.com/apialerts/apialerts-swift",
        data: ["language": "swift", "package_version": version]
    ))

switch result {
case .success(let sent):
    print("OK: swift \(version) sent to \(sent.workspace ?? "") (\(sent.channel ?? ""))")
case .failure(let error):
    fputs("FAIL: \(error.localizedDescription)\n", stderr)
    exit(1)
}
