# Integration Tests

End-to-end tests for all APIAlerts SDKs, run against the live API using a dedicated test workspace. There are two layers, and they test different things:

| Layer         | Workflow          | Source of the SDK                                                       | Catches                                                                                     |
|---------------|-------------------|-------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| **Source**    | `integration.yml` | Builds each SDK from its repo (local source)                            | Code regressions, before release                                                            |
| **Published** | `published.yml`   | Installs the package a consumer downloads (npm / Go proxy / PyPI / ...) | Broken/incomplete *publishes* (missing files, bad metadata, undeclared deps), after release |

The source layer can't catch a bad publish because it never downloads the artifact - it builds from the same tree it's testing. The published layer closes that gap: it consumes the SDK exactly as a stranger does.

## Source layer (`integration.yml`)

Each CI job checks out an SDK repo and runs its `sample/` script with a shared test API key. No SDK code lives in this repo - the sample logic lives in each SDK repo.

Each sample sends two events:
1. **Minimal** — `message` only, validates optional fields are truly optional
2. **Full** — all fields (`message`, `channel`, `event`, `title`, `tags`, `link`, `data`)

## Published layer (`published.yml`)

A tiny self-contained consumer lives in `<language>/` (e.g. `go/`, `js/`, `python/`). Each one pins a dependency on a **published** version in its own manifest (`package.json` / `go.mod` / `requirements.txt`) - never a local path - installs it, and sends one event whose payload carries the language and the resolved package version, so the alerts read "Published smoke - Go v1.2.0" at a glance. Each folder has a README with its install command.

The workflow just installs what the manifest declares and runs it - no `@latest` upgrade step, so it works uniformly across ecosystems (npm, Go proxy, PyPI, and later Gradle, SwiftPM, etc., which have no clean CLI upgrade). On release you bump the pinned version in the relevant manifest and dispatch the workflow to confirm that exact published version works. Live languages so far: **Go, JS, Python**.

## Triggers

- **Manual** — `workflow_dispatch` on either workflow, run before (source) or after (published) a coordinated release. Bump the pinned version in the `<lang>/` manifest first, then dispatch the published workflow.

## Secret required

`APIALERTS_API_KEY` — API key for a dedicated test workspace. Set in this repo's GitHub Actions secrets.

## Adding a new SDK

**Source layer:**
1. Add a `sample/` directory to the SDK repo with a script that sends a minimal and full event
2. Uncomment the corresponding job in `.github/workflows/integration.yml`
3. The Go SDK (`apialerts-go/sample/github.go`) is the reference implementation

**Published layer (once the package is live on its registry):**
1. Add `<language>/` with the tiniest project that depends on the **published** package and sends one event (see `go`, `js`, `python` for the pattern)
2. Add a job to `.github/workflows/published.yml` that installs the latest published version and runs it

## Status

| SDK                                                         | Sample ready | Job active |
|-------------------------------------------------------------|--------------|------------|
| [C#](https://github.com/apialerts/apialerts-csharp)         | ✅            | ✅          |
| [CLI](https://github.com/apialerts/cli)                     | ✅            | ✅          |
| [Dart](https://github.com/apialerts/apialerts-dart)         | ✅            | ✅          |
| [Go](https://github.com/apialerts/apialerts-go)             | ✅            | ✅          |
| [Godot](https://github.com/apialerts/apialerts-godot)       | ✅            | ✅          |
| [JS](https://github.com/apialerts/apialerts-js)             | ✅            | ✅          |
| [Kotlin](https://github.com/apialerts/apialerts-kotlin)     | ✅            | ✅          |
| [Notify Action](https://github.com/apialerts/notify-action) | ✅            | ✅          |
| [PHP](https://github.com/apialerts/apialerts-php)           | ✅            | ✅          |
| [Python](https://github.com/apialerts/apialerts-python)     | ✅            | ✅          |
| [Ruby](https://github.com/apialerts/apialerts-ruby)         | ✅            | ✅          |
| [Rust](https://github.com/apialerts/apialerts-rust)         | ✅            | ✅          |
| [Swift](https://github.com/apialerts/apialerts-swift)       | ✅            | ✅          |
