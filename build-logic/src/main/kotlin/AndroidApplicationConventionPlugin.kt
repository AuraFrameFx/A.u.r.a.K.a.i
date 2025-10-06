override fun apply(target: Project) {
    with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        // Wait for Android plugin to be fully ready before configuring
        pluginManager.withPlugin("com.android.application") {
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

                    // Lint configuration (per AGENT_INSTRUCTIONS.md section 6)lint {
                        warningsAsErrors = false
                        abortOnError = false
                        disable.addAll(listOf("InvalidPackage", "OldTargetApi"))
                    }
                }

                extensions.configure<JavaPluginExtension>("java") {
                    sourceCompatibility = JavaVersion.VERSION_24
                    targetCompatibility = JavaVersion.VERSION_24
                }

                // Clean tasks for app module(per AGENT_INSTRUCTIONS.md section 4)
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