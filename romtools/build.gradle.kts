plugins {
    id("com.android.library")
    id("com.android.base")  // AGP 9 alpha workaround

    id("com.android.base") // AGP 9.0.0-alpha09 workaround: Hilt auto-applies when detecting this
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler) // Required for Compose in Kotlin 2.0+
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10" // Match this to your Compose BOM version
    }
}

    kotlin {
        jvmToolchain(24)
    }

    val romToolsOutputDirectory: DirectoryProperty =
    project.objects.directoryProperty().convention(layout.buildDirectory.dir("rom-tools"))

    dependencies {
        api(project(":core-module"))
        implementation(project(":secure-comm"))
        implementation(libs.androidx.core.ktx)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.bundles.compose.ui)
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.navigation.compose)
        implementation(libs.bundles.androidx.core)
        implementation(libs.hilt.android)
        add("ksp", libs.hilt.compiler)
        implementation(libs.bundles.coroutines)
        implementation(libs.bundles.network)
        implementation(libs.androidx.room.runtime)
        implementation(libs.androidx.room.ktx)
        add("ksp", libs.androidx.room.compiler)
        implementation(libs.bundles.firebase)
        implementation(libs.timber)
        implementation(libs.coil.compose)
        debugImplementation(libs.leakcanary.android)
        debugImplementation(libs.androidx.compose.ui.tooling)
        testImplementation(libs.bundles.testing.unit)
        testImplementation(libs.mockk.android)
        androidTestImplementation(libs.mockk.android)
        testImplementation(libs.hilt.android.testing)
        androidTestImplementation(libs.hilt.android.testing)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        // androidTestImplementation(libs.hilt.android.testing); kspAndroidTest(libs.hilt.compiler)
        implementation(kotlin("stdlib-jdk8"))
        implementation("androidx.compose.material:material-icons-extended")
        implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
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

    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"
        }
    }
}
