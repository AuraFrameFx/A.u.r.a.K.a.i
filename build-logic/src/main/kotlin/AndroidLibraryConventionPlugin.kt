// ==== GENESIS PROTOCOL - ANDROID LIBRARY CONVENTION ====
// Standard Android library configuration for all modules
// AGP 9.0+ with built-in Kotlin support

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {
    /**
     * Applies Android library plugin and configures Kotlin defaults.
     *
     * Configures the target project's Android LibraryExtension and Kotlin JVM toolchain:
     * - Sets Android compileSdk to 36 and default minSdk to 34.
     * - Sets Java sourceCompatibility and targetCompatibility to Java 24.
     * - Configures the Kotlin JVM toolchain to use Java 24 (applied only after the Kotlin Android plugin is present).
     *
     * @param target The Gradle project to configure; this method mutates the project's plugins and extensions.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply plugins in correct order for AGP 9.0 compatibility
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")  // Apply Kotlin FIRST
                // Removed: apply("com.google.devtools.ksp")  // KSP must be applied in the module, not here
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<LibraryExtension> {
                compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

                defaultConfig {
                    minSdk = libs.findVersion("minSdk").get().toString().toInt()
                }

                // ✅ Java 24 for consistency across all modules (Firebase requirement)
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_24
                    targetCompatibility = JavaVersion.VERSION_24
                }
            }
            // ✅ Configure Kotlin for Android
            extensions.findByType(KotlinAndroidProjectExtension::class.java)?.apply {
                // jvmToolchain(24) // Removed: not supported in Android modules
                compilerOptions {
                    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
                    freeCompilerArgs.addAll(
                        "-opt-in=kotlin.RequiresOptIn",
                        "-Xjvm-default=all"
                    )
                }
            }
        }
    }
}