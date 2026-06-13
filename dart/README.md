# Dart - published package smoke test

Tiny consumer of the **published** `apialerts` pub.dev package. It configures the SDK and sends one event, proving the published package installs, resolves, and works end to end against the live API.

## Install

```bash
dart pub get
```

## Run

```bash
APIALERTS_API_KEY=<test-workspace-key> dart run bin/smoke.dart
```

## Pinned version

The version under test is pinned in [`pubspec.yaml`](pubspec.yaml):

```yaml
apialerts: 1.0.0
```

On release, bump that pin and dispatch the **Published Package Smoke Tests** workflow.
