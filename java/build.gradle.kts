plugins {
    java
    application
}

repositories {
    mavenCentral()
}

// Published version under test. Bump on release.
val sdkVersion = "1.1.0"

dependencies {
    implementation("com.apialerts:client:$sdkVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("Main")
}
