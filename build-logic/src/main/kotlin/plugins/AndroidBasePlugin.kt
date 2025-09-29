package plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidBasePlugin : Plugin<Project> {
    /**
     * Applies the Android library plugin to the given project and configures Android and Java compatibility defaults.
     *
     * Configured defaults (applied when the Android library extension is present): compileSdk = 35, minSdk = 33,
     * targetSdk = 35, and both Java source and target compatibility set to JavaVersion.VERSION_21.
     */
    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.extensions.findByType(LibraryExtension::class.java)?.apply {
            compileSdk = 35
            defaultConfig.apply {
                minSdk = 33
                targetSdk = 35
            }
            compileOptions.apply {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
        }
    }
}
