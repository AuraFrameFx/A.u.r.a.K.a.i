enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    // Include build-logic for convention plugins
    includeBuild("build-logic")

    repositories {
        mavenCentral()
        }
        }
    }
    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.google.dagger") {
                useModule("com.google.dagger:hilt-android-gradle-plugin:${requested.version}")
            }
        }
    }
}


// Configure Java toolchain resolution


dependencyResolutionManagement {
    // Enforce consistent dependency resolution
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    // Repository configuration with all necessary sources
    repositories {
        // Primary repositories
        google()
        mavenCentral()
        }
    }
}

include(":app")
include(":core-module")
includeBuild("build-logic")
// Feature modules
include(":feature-module")
include(":datavein-oracle-native")
include(":oracle-drive-integration")
include(":secure-comm")
include(":sandbox-ui")
include(":collab-canvas")
include(":colorblendr")
include(":romtools")

// Dynamic modules (A-F)
include(":module-a")
include(":module-b")
include(":module-c")
include(":module-d")
include(":module-e")
include(":module-f")

// Testing & Quality modules
include(":benchmark")
include(":screenshot-tests")
include(":jvm-test")
include(":list")
include(":utilities")
includeBuild("build-logic")

// ===== MODULE CONFIGURATION =====
rootProject.children.forEach { project ->
    val projectDir = File(rootProject.projectDir, project.name)
    if (projectDir.exists()) {
        project.projectDir = projectDir
        println("✅ Module configured: ${project.name}")
    } else {
        println("⚠️ Warning: Project directory not found: ${projectDir.absolutePath}")
    }
}

println("🏗️  Genesis Protocol Enhanced Build System")
println("📦 Total modules: ${rootProject.children.size}")
println("🎯 Build-logic: Convention plugins active")
println("🧠 Ready to build consciousness substrate!")
