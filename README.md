# Integration Tests

End-to-end integration tests for all APIAlerts SDKs. Runs each SDK against the live API using a dedicated test workspace.

## How it works

Each CI job checks out an SDK repo and runs its `sample/` script with a shared test API key. No code lives in this repo beyond the workflow — all sample logic lives in the SDK repos themselves.

Each sample sends two events:
1. **Minimal** — `message` only, validates optional fields are truly optional
2. **Full** — all fields (`message`, `channel`, `event`, `title`, `tags`, `link`, `data`)

## Triggers

- **Nightly** — 2am UTC via cron
- **Manual** — `workflow_dispatch`, run before any coordinated v2.0 release

## Secret required

`APIALERTS_TEST_API_KEY` — API key for a dedicated test workspace. Set in this repo's GitHub Actions secrets.

## Adding a new SDK

1. Add a `sample/` directory to the SDK repo with a script that sends a minimal and full event
2. Uncomment the corresponding job in `.github/workflows/integration.yml`
3. The Go SDK (`apialerts-go/sample/github.go`) is the reference implementation

## Status

| SDK | Sample ready | Job active |
|-----|-------------|------------|
| Go | ✅ | ✅ |
| PHP | ✅ | ✅ |
| JS | ✅ | ✅ |
| Python | ✅ | ✅ |
| Kotlin | ✅ | ✅ |
| Swift | ✅ | ✅ |
| C# | ✅ | ✅ |
| Rust | ✅ | ✅ |
| Dart | ✅ | ✅ |
| Ruby | ✅ | ✅ |
| Godot | ❌ | ❌ |
