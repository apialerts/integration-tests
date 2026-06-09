// swift-tools-version: 6.0

import PackageDescription

let package = Package(
    name: "smoke",
    platforms: [.macOS(.v13)],
    dependencies: [
        .package(url: "https://github.com/apialerts/apialerts-swift", exact: "1.2.0")
    ],
    targets: [
        .executableTarget(
            name: "smoke",
            dependencies: [.product(name: "APIAlerts", package: "apialerts-swift")]
        )
    ]
)
