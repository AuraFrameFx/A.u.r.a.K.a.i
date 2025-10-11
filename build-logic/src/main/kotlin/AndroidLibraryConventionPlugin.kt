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
     * Applies Android library plugin and configures Kotlin settings.
     * 
     * AGP 9.0+ includes built-in Kotlin support - no need to apply kotlin-android plugin separately.
     *
     * @param target The Gradle project to apply the plugin to
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply plugins in correct order for AGP 9.0 compatibility
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")  // Apply Kotlin FIRST
                apply("com.android.library")  // Then Android
                apply("com.google.devtools.ksp")  // Then KSP for annotation processing
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
            
            // ✅ Configure Kotlin via AGP's built-in support (AGP 9.0+)
            extensions.findByType(KotlinAndroidProjectExtension::class.java)?.apply {
                jvmToolchain(24)
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
