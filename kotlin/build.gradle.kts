plugins {
    kotlin("jvm") version "2.4.0"
    application
}

repositories {
    mavenCentral()
}

// Published version under test. Bump on release.
val sdkVersion = "1.1.0"

dependencies {
    implementation("com.apialerts:client:$sdkVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
    applicationDefaultJvmArgs = listOf("-DsdkVersion=$sdkVersion")
}
