import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.configure

class YukiHookAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Configure Android applications for Yuki Hook
            pluginManager.withPlugin("com.android.application") {
                extensions.configure<ApplicationExtension> {
                    configureAndroidApplication(this)
                }
            }
            
            // Configure Android libraries for Yuki Hook
            pluginManager.withPlugin("com.android.library") {
                extensions.configure<LibraryExtension> {
                    configureAndroidLibrary(this)
                }
            }
        }
    }

    /**
     * Configure Android Application for Yuki Hook API integration
     */
    private fun configureAndroidApplication(extension: ApplicationExtension) {
        extension.apply {
            compileSdk = 36
            
            defaultConfig {
                minSdk = 34
                targetSdk = 36
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
            
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_24
                targetCompatibility = JavaVersion.VERSION_24
            }
        }
    }
    
    /**
     * Configure Android Library for Yuki Hook API integration
     */
    private fun configureAndroidLibrary(extension: LibraryExtension) {
        extension.apply {
            compileSdk = 36
            
            defaultConfig {
                minSdk = 34
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }
            
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
            
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_24
                targetCompatibility = JavaVersion.VERSION_24
            }
        }
    }
}
