# Swift - published package smoke test

Tiny consumer of the **published** `apialerts-swift` package via Swift Package Manager. It configures the SDK and sends one event, proving the published package resolves, builds, and works end to end against the live API.

## Run

```bash
APIALERTS_API_KEY=<test-workspace-key> swift run
```

## Pinned version

The version under test is pinned in [`Package.swift`](Package.swift):

```swift
.package(url: "https://github.com/apialerts/apialerts-swift", exact: "1.2.0")
```

> **Not runnable yet.** There is no `1.2.0` tag on apialerts-swift until it is released, so the package will not resolve. On release, bump this pin (and the `version` literal in [`Sources/smoke/main.swift`](Sources/smoke/main.swift)) and dispatch the **Published Package Smoke Tests** workflow.
