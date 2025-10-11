import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.configure

class YukiHookAndroidConventionPlugin : Plugin<Project> {
    /**
     * Registers Android-specific configuration actions on the given Gradle project.
     *
     * When the Android application plugin is applied, configures the project's
     * ApplicationExtension via configureAndroidApplication. When the Android
     * library plugin is applied, configures the project's LibraryExtension via
     * configureAndroidLibrary.
     *
     * @param target The Gradle project to which the Android configuration actions will be applied.
     */
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
     * Apply Android application defaults required by the Yuki Hook integration.
     *
     * Configures compile SDK, defaultConfig (minSdk, targetSdk, and instrumentation runner),
     * the release build type (minification and ProGuard files), and Java compile options.
     *
     * @param extension The Android ApplicationExtension to configure. 
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
     * Configure the Android library extension with Yuki Hook required build defaults.
     *
     * Applies:
     * - compileSdk = 36
     * - defaultConfig: minSdk = 34, testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner", and consumer ProGuard rules file
     * - release build type: minification disabled and ProGuard files including the Android optimize file and "proguard-rules.pro"
     *
     * @param extension The LibraryExtension to configure.
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
        }
    }
}