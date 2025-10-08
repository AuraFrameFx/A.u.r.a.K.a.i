import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.typeOf
import org.gradle.api.JavaVersion

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
                // Removed apply("genesis.android.hilt") to avoid premature Hilt application
            }

            pluginManager.withPlugin("com.android.application") {
                extensions.configure(typeOf<ApplicationExtension>()) {
                    compileSdk = 36
                    defaultConfig.targetSdk = 36
                    defaultConfig.minSdk = 34
                    defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    defaultConfig.vectorDrawables.useSupportLibrary = true

                    buildTypes.getByName("release").apply {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles.add(getDefaultProguardFile("proguard-android-optimize.txt"))
                        proguardFiles.add(file("proguard-rules.pro"))
                    }

                    buildFeatures.compose = true
                    buildFeatures.buildConfig = true
                    buildFeatures.viewBinding = false
                    buildFeatures.dataBinding = false

                    compileOptions.sourceCompatibility = JavaVersion.VERSION_24
                    compileOptions.targetCompatibility = JavaVersion.VERSION_24
                    compileOptions.isCoreLibraryDesugaringEnabled = true

                    packaging.resources.excludes.addAll(setOf(
                        "/META-INF/{AL2.0,LGPL2.1}",
                        "/META-INF/AL2.0",
                        "/META-INF/LGPL2.1",
                        "/META-INF/DEPENDENCIES",
                        "/META-INF/LICENSE",
                        "/META-INF/LICENSE.txt",
                        "/META-INF/NOTICE",
                        "/META-INF/NOTICE.txt",
                        "META-INF/*.kotlin_module",
                        "**/kotlin/**",
                        "**/*.txt"
                    ))
                    packaging.jniLibs.useLegacyPackaging = false
                    packaging.jniLibs.pickFirsts.addAll(listOf("**/libc++_shared.so", "**/libjsc.so"))

                    lint.abortOnError = false
                    lint.warningsAsErrors = false
                    lint.disable.addAll(listOf("InvalidPackage", "OldTargetApi"))
                }

                extensions.configure<JavaPluginExtension>("java") {
                    sourceCompatibility = JavaVersion.VERSION_24
                    targetCompatibility = JavaVersion.VERSION_24
                }

                tasks.register<Delete>("cleanKspCache") {
                    group = "build setup"
                    description = "Clean KSP caches (fixes NullPointerException)"
                    delete(
                        project.layout.buildDirectory.dir("generated/ksp"),
                        project.layout.buildDirectory.dir("tmp/kapt3"),
                        project.layout.buildDirectory.dir("tmp/kotlin-classes"),
                        project.layout.buildDirectory.dir("kotlin"),
                        project.layout.buildDirectory.dir("generated/source/ksp")
                    )
                }

                tasks.named("preBuild") {
                    dependsOn("cleanKspCache")
                }
            }

            pluginManager.withPlugin("org.jetbrains.kotlin.android") {
                extensions.getByType<org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension>().jvmToolchain(24)
            }
        }
    }
}