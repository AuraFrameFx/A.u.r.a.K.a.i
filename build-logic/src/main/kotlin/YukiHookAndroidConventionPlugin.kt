import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.JavaVersion

class AurakaiAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.withId("com.android.application") {
            val androidExt = target.extensions.findByName("android")
            if (androidExt != null) {
                configureAndroid(androidExt)
            }
        }
        target.plugins.withId("com.android.library") {
            val androidExt = target.extensions.findByName("android")
            if (androidExt != null) {
                configureAndroid(androidExt)
            }
        }
    }

    private fun configureAndroid(androidExt: Any) {
        if (androidExt is BaseExtension) {
            androidExt.compileSdkVersion(36)
            val defaultConfig = androidExt.defaultConfig
            defaultConfig.minSdkVersion(33)
            defaultConfig.targetSdkVersion(36)
            defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            try {
                defaultConfig.consumerProguardFiles("consumer-rules.pro")
            } catch (_: Exception) {}
            val release = androidExt.buildTypes.getByName("release")
            release.isMinifyEnabled = true
            release.proguardFiles(
                androidExt.getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            val compileOptions = androidExt.javaClass.getMethod("getCompileOptions").invoke(androidExt)
            compileOptions.javaClass.getMethod("setSourceCompatibility", JavaVersion::class.java)
                .invoke(compileOptions, JavaVersion.VERSION_1_8)
            compileOptions.javaClass.getMethod("setTargetCompatibility", JavaVersion::class.java)
                .invoke(compileOptions, JavaVersion.VERSION_1_8)

            // Set Kotlin compiler options for Android (reflection, AGP-compatible)
            try {
                val kotlinOptions = androidExt.javaClass.getMethod("getKotlinOptions").invoke(androidExt)
                kotlinOptions.javaClass.getMethod("setJvmTarget", String::class.java).invoke(kotlinOptions, "1.8")
                kotlinOptions.javaClass.getMethod("setFreeCompilerArgs", List::class.java)
                    .invoke(kotlinOptions, listOf("-Xjvm-default=all", "-opt-in=kotlin.RequiresOptIn"))
            } catch (_: Exception) {}
        }
    }
}
