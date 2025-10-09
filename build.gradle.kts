import org.gradle.api.GradleException
import java.io.File

// Apply plugins to root project to avoid multiple loading warnings
plugins {
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    // Build logic convention plugins
    id("genesis.android.native") apply false
}

tasks.register<Delete>("clean") {
    group = "build"
    description = "Delete root build directory"
    delete(rootProject.layout.buildDirectory)

    doLast {
        println("🧹 Cleaned root build directory")
    }
}
