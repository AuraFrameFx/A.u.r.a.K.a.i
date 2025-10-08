package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.withPlugin("com.android.library") { applyHilt() }
            pluginManager.withPlugin("com.android.application") { applyHilt() }
        }
    }
}

private fun Project.applyHilt() {
    with(pluginManager) {
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
    }
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
        "implementation"(libs.findLibrary("hilt.android").get())
        "ksp"(libs.findLibrary("hilt.compiler").get())
        "androidTestImplementation"(libs.findLibrary("hilt.android.testing").get())
        "kspAndroidTest"(libs.findLibrary("hilt.compiler").get())
    }
}

