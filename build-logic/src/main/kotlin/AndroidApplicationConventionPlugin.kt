import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.*

class AndroidApplicationConventionPlugin : Plugin<Project> {
    /**
     * Applies core plugins (Android application, Kotlin Compose), and configures the Android
     * ApplicationExtension with sensible defaults for compile/target/min SDKs, build types,
     * Compose, packaging, lint, and desugaring. Also sets Java/Kotlin JVM toolchains to Java 24,
     * registers a `cleanKspCache` task to remove KSP/KAPT generated caches, and makes `preBuild`
     * depend on that clean task.
     *
     * @param target The Gradle project the plugin is being applied to.
     */
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            // Wait for base plugin to be ready, then apply Hilt
            pluginManager.withPlugin("com.android.base") {
                pluginManager.apply("com.google.dagger.hilt.android")
                pluginManager.apply("com.google.devtools.ksp")
                pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

                // Configure Hilt dependencies
                val libs =
                    extensions.getByType(org.gradle.api.artifacts.VersionCatalogsExtension::class.java)
                        .named("libs")
                dependencies {
                    add("implementation", libs.findLibrary("hilt-android").get())
                    add("ksp", libs.findLibrary("hilt-compiler").get())
                }

                extensions.configure<ApplicationExtension> {
                    compileSdk = 36

                    defaultConfig {
                        targetSdk = 36
                        minSdk = 34

                        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                        vectorDrawables {
                            useSupportLibrary = true
                        }
                    }

                    buildTypes {
                        release {
                            isMinifyEnabled = true
                            isShrinkResources = true
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }

                    buildFeatures {
                        compose = true
                        buildConfig = true
                        viewBinding = false
                        dataBinding = false
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_24
                        targetCompatibility = JavaVersion.VERSION_24
                        // App module DOES get desugaring dependency
                        isCoreLibraryDesugaringEnabled = true
                    }

                    packaging {
                        resources {
                            excludes += setOf(
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
                            )
                        }
                        jniLibs {
                            useLegacyPackaging = false
                            pickFirsts += listOf("**/libc++_shared.so", "**/libjsc.so")
                        }
                    }

                    lint {
                        warningsAsErrors = false
                        abortOnError = false
                        disable.addAll(listOf("InvalidPackage", "OldTargetApi"))
                    }
                }

                extensions.configure<JavaPluginExtension>("java") {
                    sourceCompatibility = JavaVersion.VERSION_24
                    targetCompatibility = JavaVersion.VERSION_24
                }

                // Clean tasks for app module
                tasks.register("cleanKspCache", Delete::class.java) {
                    group = "build setup"
                    description = "Clean KSP caches (fixes NullPointerException)"
                    delete(
                        layout.buildDirectory.dir("generated/ksp"),
                        layout.buildDirectory.dir("tmp/kapt3"),
                        layout.buildDirectory.dir("tmp/kotlin-classes"),
                        layout.buildDirectory.dir("kotlin"),
                        layout.buildDirectory.dir("generated/source/ksp")
                    )
                }

                tasks.named("preBuild") {
                    dependsOn("cleanKspCache")
                }
            }
            // Kotlin JVM toolchain (only configure after kotlin-android is applied)
            pluginManager.withPlugin("org.jetbrains.kotlin.android") {
                extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension> {
                    jvmToolchain(24)
                }
            }
        }
    }
}