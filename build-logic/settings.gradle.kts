// ==== GENESIS PROTOCOL - BUILD LOGIC SETTINGS ====

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// Build-logic only contains convention plugins, no app modules

rootProject.name = "build-logic"

