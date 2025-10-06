import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Defer applying Hilt until the Android Application or Library plugin is ready
            pluginManager.withPlugin("com.android.application") {
                applyHilt()
            }
            pluginManager.withPlugin("com.android.library") {
                applyHilt()
            }
        }
    }
}

private fun Project.applyHilt() {
    // Apply the necessary plugins
    with(pluginManager) {
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
    }

    // Access the version catalog correctly
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    // Add Hilt dependencies from the version catalog
    dependencies {
        "implementation"(libs.findLibrary("hilt.android").get())
        "ksp"(libs.findLibrary("hilt.compiler").get())
        "androidTestImplementation"(libs.findLibrary("hilt.android.testing").get())
        "kspAndroidTest"(libs.findLibrary("hilt.compiler").get())
    }
}