# C# - published package smoke test

Tiny consumer of the **published** `apialerts` package from NuGet. It configures the SDK and sends one event, proving the published package installs, references, and works end to end against the live API.

## Install

```bash
dotnet add package apialerts
```

## Run

```bash
APIALERTS_API_KEY=<test-workspace-key> dotnet run
```

## Pinned version

The version under test is pinned in [`Smoke.csproj`](Smoke.csproj):

```xml
<PackageReference Include="apialerts" Version="1.0.0" />
```

> **Not runnable yet.** NuGet only has old `1.0.0-alphaN` builds (pre-redesign API). This consumer uses the current `ApiAlerts` singleton API, so it won't restore until `1.0.0` is published. On release, bump this pin and dispatch the **Published Package Smoke Tests** workflow.
