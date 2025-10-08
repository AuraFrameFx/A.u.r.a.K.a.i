plugins {
    id("genesis.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
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
    // Core dependencies (Compose BOM provided by convention plugin)
    api(project(":core-module"))
    implementation(project(":secure-comm"))

    // Hilt (manually added)
    implementation(libs.hilt.android)
    implementation(libs.androidx.material3)
    ksp(libs.hilt.compiler)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    
    // Compose (BOM manages versions)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material.icons.extended)
    
    // Navigation
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
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
    kspAndroidTest(libs.hilt.compiler)
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
