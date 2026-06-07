# Kotlin (JVM) - published package smoke test

Tiny Gradle JVM consumer of the published `com.apialerts:client` from Maven
Central. Sends one event, proving the artifact resolves and is usable.

## Install (Gradle)

```kotlin
implementation("com.apialerts:client:1.1.0")
```

## Run

```bash
APIALERTS_API_KEY=<test-workspace-key> ./gradlew run
```

## Pinned version

`val sdkVersion` in [`build.gradle.kts`](build.gradle.kts). Bump on release, then
dispatch the Published Package Smoke Tests workflow.
