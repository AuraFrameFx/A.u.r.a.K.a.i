plugins {
    id("genesis.android.library")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.romtools"
    compileSdk = 36

    defaultConfig {
        minSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }
    buildFeatures {
        compose = true
    }
}

    val romToolsOutputDirectory: DirectoryProperty =
    project.objects.directoryProperty().convention(layout.buildDirectory.dir("rom-tools"))

dependencies {
    // Core dependencies (Hilt, Compose BOM, etc. provided by convention plugins)
    api(project(":core-module"))
    implementation(project(":secure-comm"))
    
    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    
    // Compose & Navigation
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.hilt.navigation.compose)
    
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    
    // Network & Firebase
    implementation(libs.bundles.network)
    implementation(libs.bundles.firebase)
    
    // Additional
    implementation(libs.timber)
    implementation(libs.coil.compose)
    debugImplementation(libs.leakcanary.android)
    debugImplementation(libs.androidx.compose.ui.tooling)
    
    // Testing
    testImplementation(libs.bundles.testing.unit)
    testImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.hilt.android.testing)
}

// Copy task
    tasks.register<Copy>("copyRomTools") {
        from("src/main/resources")
        into(romToolsOutputDirectory)
        include("**/*.so", "**/*.bin", "**/*.img", "**/*.jar")
        includeEmptyDirs = false
        doFirst { romToolsOutputDirectory.get().asFile.mkdirs(); logger.lifecycle("📁 ROM tools directory: ${romToolsOutputDirectory.get().asFile}") }
        doLast { logger.lifecycle("✅ ROM tools copied to: ${romToolsOutputDirectory.get().asFile}") }
    }

// Verification task
    tasks.register("verifyRomTools") {
        dependsOn("copyRomTools")
    }

    tasks.named("build") { dependsOn("verifyRomTools") }

    tasks.register("romStatus") {
        group = "aegenesis"; doLast { println("🛠️ ROM TOOLS - Ready (Java 24)") }
    }

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
        dokkaSourceSets {
        named("main") {
            sourceRoots.from(file("src/main/java"))
            sourceRoots.from(file("src/main/kotlin"))
            sourceRoots.from(file("src/main/res"))
        }
        }
    }

