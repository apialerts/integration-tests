# Java - published package smoke test

Java consumer of the **published** `com.apialerts:client` from Maven Central
(the Kotlin Multiplatform artifact). Proves the JVM variant resolves and the
Java surface (`EventBuilder`, `ApiAlertsJvm.client`, `sendFuture`) works.

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
