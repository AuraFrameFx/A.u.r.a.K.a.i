// ==== GENESIS PROTOCOL - MAIN APPLICATION ====
// This build script now uses the custom convention plugins for a cleaner setup.

plugins {
}

android {
    defaultConfig {
    }

    buildFeatures {
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
        freeCompilerArgs.addAll(
            "-Xjvm-default=all",
            "-Xopt-in=kotlin.RequiresOptIn",
        )
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(project(":feature-module"))
    implementation(project(":oracle-drive-integration"))
    implementation(project(":romtools"))
    implementation(project(":secure-comm"))
    implementation(project(":collab-canvas"))
    implementation(project(":colorblendr"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.androidx.test.espresso.core)

    implementation(libs.timber)
    implementation(libs.coil.compose)
    
    // ===== XPOSED/LSPosed Integration =====
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))

    // --- TESTING ---
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
    debugImplementation(libs.leakcanary.android)

    // Multidex
    implementation("androidx.multidex:multidex:2.0.1")
}

openApiGenerate {
    generatorName = "kotlin"
    inputSpec = "$rootDir/app/api/unified-aegenesis-api.yml"
    outputDir = "$rootDir/app/src/main/kotlin"
    apiPackage = "dev.aurakai.auraframefx.api"
    modelPackage = "dev.aurakai.auraframefx.model"
    invokerPackage = "dev.aurakai.auraframefx.invoker"
    configOptions =
        mapOf(
            "library" to "jvm-retrofit2",
            "dateLibrary" to "java8",
        )
    // Ensure no empty meta or supportingFiles properties
    // Remove or comment out any empty or unused properties
}
