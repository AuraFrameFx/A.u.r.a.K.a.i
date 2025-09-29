package plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidBasePlugin : Plugin<Project> {
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
