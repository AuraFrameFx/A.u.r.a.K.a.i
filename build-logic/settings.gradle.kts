// ==== GENESIS PROTOCOL - BUILD LOGIC SETTINGS ====

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://repo.gradle.org/gradle/libs-releases")
        }
    }
}

// Build-logic only contains convention plugins, no app modules

rootProject.name = "build-logic"
