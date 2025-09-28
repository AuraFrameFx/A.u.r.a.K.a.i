package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion

class AndroidBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.extensions.findByType(LibraryExtension::class.java)?.apply {
            compileSdk = 36
            defaultConfig.apply {
                minSdk = 24
                targetSdk = 36
            }
            compileOptions.apply {
                sourceCompatibility = JavaVersion.VERSION_24
                targetCompatibility = JavaVersion.VERSION_24
            }
        }
    }
}
