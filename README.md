# Integration Tests

End-to-end integration tests for all APIAlerts SDKs. Runs each SDK against the live API using a dedicated test workspace.

## How it works

Each CI job checks out an SDK repo and runs its `sample/` script with a shared test API key. No code lives in this repo beyond the workflow — all sample logic lives in the SDK repos themselves.

Each sample sends two events:
1. **Minimal** — `message` only, validates optional fields are truly optional
2. **Full** — all fields (`message`, `channel`, `event`, `title`, `tags`, `link`, `data`)

## Triggers

- **Manual** — `workflow_dispatch`, run before any coordinated release

## Secret required

`APIALERTS_TEST_API_KEY` — API key for a dedicated test workspace. Set in this repo's GitHub Actions secrets.

## Adding a new SDK

1. Add a `sample/` directory to the SDK repo with a script that sends a minimal and full event
2. Uncomment the corresponding job in `.github/workflows/integration.yml`
3. The Go SDK (`apialerts-go/sample/github.go`) is the reference implementation

## Status

| SDK | Sample ready | Job active |
|-----|-------------|------------|
| [C#](https://github.com/apialerts/apialerts-csharp) | ✅ | ✅ |
| [CLI](https://github.com/apialerts/cli) | ✅ | ✅ |
| [Dart](https://github.com/apialerts/apialerts-dart) | ✅ | ✅ |
| [Go](https://github.com/apialerts/apialerts-go) | ✅ | ✅ |
| [Godot](https://github.com/apialerts/apialerts-godot) | ✅ | ✅ |
| [JS](https://github.com/apialerts/apialerts-js) | ✅ | ✅ |
| [Kotlin](https://github.com/apialerts/apialerts-kotlin) | ✅ | ✅ |
| [Notify Action](https://github.com/apialerts/notify-action) | ✅ | ✅ |
| [PHP](https://github.com/apialerts/apialerts-php) | ✅ | ✅ |
| [Python](https://github.com/apialerts/apialerts-python) | ✅ | ✅ |
| [Ruby](https://github.com/apialerts/apialerts-ruby) | ✅ | ✅ |
| [Rust](https://github.com/apialerts/apialerts-rust) | ✅ | ✅ |
| [Swift](https://github.com/apialerts/apialerts-swift) | ✅ | ✅ |
