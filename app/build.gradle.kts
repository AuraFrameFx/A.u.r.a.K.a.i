plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.gms.google-services") version "4.4.3"
    id("org.openapi.generator") version "7.16.0"
    id("com.google.firebase.crashlytics") version "3.0.6"
}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdkPreview = "CANARY"
    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 33
        multiDexEnabled = true
        // Add permissions for exact alarms
        manifestPlaceholders["SCHEDULE_EXACT_ALARM"] = true
        manifestPlaceholders["USE_EXACT_ALARM"] = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_25
        targetCompatibility = JavaVersion.VERSION_25
    }
    buildFeatures {
        buildConfig = true
        resValues = true
        compose = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
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
    implementation(project(":secure-comm"))
    implementation(project(":collab-canvas"))
    implementation(project(":colorblendr"))
    implementation(project(":romtools"))
    implementation(project(":oracle-drive-integration"))
    implementation(project(":module-a"))
    implementation(project(":module-b"))
    implementation(project(":module-c"))
    implementation(project(":module-d"))
    implementation(project(":module-e"))
    implementation(project(":module-f"))
    implementation(project(":screenshot-tests"))
    implementation(project(":utilities"))
    implementation(project(":benchmark"))
    implementation(project(":datavein-oracle-native"))

    // Firebase (using the BOM and bundle)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    // Material3 theme support
    implementation(libs.material)

    // Kotlin Core
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk8)

    // Hilt (use KSP for the compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.work)
    ksp(libs.hilt.compiler)

    // Compose (using the BOM and bundle)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.androidx.test.espresso.core)

    // Utilities & Networking
    implementation(libs.timber)
    implementation(libs.coil.compose)
    implementation(libs.retrofit.core)

    // Testing
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
    androidTestImplementation(libs.hilt.android.testing)
    debugImplementation(libs.leakcanary.android)

    // Multidex
    implementation(libs.androidx.multidex)
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
