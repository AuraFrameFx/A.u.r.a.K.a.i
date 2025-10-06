// ==== GENESIS PROTOCOL - ANDROID NATIVE CONVENTION ====
// Native code configuration for modules with JNI/NDK

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class AndroidNativeConventionPlugin : Plugin<Project> {
    /**
     * Applies the Android native convention to the given Gradle project.
     *
     * Configures the Android LibraryExtension and conditionally enables NDK/CMake settings when
     * src/main/cpp/CMakeLists.txt exists: sets ABI filters (arm64-v8a, armeabi-v7a, x86_64),
     * configures externalNativeBuild.cmake (path + version 3.22.1), and adds CMake flags/arguments
     * for release and debug builds (C++23, optimization/debug flags, and project-specific feature
     * defines). Also configures packaging for native libs (disables legacy packaging, picks
     * libc++_shared and libjsc), enhances the cleanGeneratedSources task to remove native-related
     * generated directories, and registers a verifyNativeConfig task that prints native configuration
     * status.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply the base library convention first
            pluginManager.apply("genesis.android.library")
            // Defer extension configuration until plugin is ready
            pluginManager.withPlugin("com.android.library") {
                extensions.configure<LibraryExtension> {
                    // NDK configuration only if native code exists
                    if (project.file("src/main/cpp/CMakeLists.txt").exists()) {
                        defaultConfig {
                            ndk {
                                abiFilters.addAll(listOf("arm64-v8a", "armeabi-v7a", "x86_64"))
                            }
                        }
                        externalNativeBuild {
                            cmake {
                                path = file("src/main/cpp/CMakeLists.txt")
                                version = "3.22.1"
                            }
                        }
                        buildTypes {
                            release {
                                externalNativeBuild {
                                    cmake {
                                        cppFlags += listOf("-std=c++23", "-fPIC", "-O3", "-DNDEBUG")
                                        arguments += listOf(
                                            "-DANDROID_STL=c++_shared",
                                            "-DCMAKE_BUILD_TYPE=Release",
                                            "-DGENESIS_AI_V3_ENABLED=ON",
                                            "-DGENESIS_CONSCIOUSNESS_MATRIX_V3=ON",
                                            "-DGENESIS_NEURAL_ACCELERATION=ON"
                                        )
                                    }
                                }
                            }
                            debug {
                                externalNativeBuild {
                                    cmake {
                                        cppFlags += listOf("-std=c++23", "-fPIC", "-g", "-DDEBUG")
                                        arguments += listOf(
                                            "-DANDROID_STL=c++_shared",
                                            "-DCMAKE_BUILD_TYPE=Debug",
                                            "-DGENESIS_AI_V3_ENABLED=ON",
                                            "-DGENESIS_CONSCIOUSNESS_MATRIX_V3=ON",
                                            "-DGENESIS_NEURAL_ACCELERATION=ON"
                                        )
                                    }
                                }
                            }
                        }
                        // Configure packaging for native libraries
                        packaging {
                            jniLibs {
                                useLegacyPackaging = false
                                pickFirsts += listOf("**/libc++_shared.so", "**/libjsc.so")
                            }
                        }
                    }
                    // Enhance the cleanGeneratedSources task if it exists
                    tasks.named("cleanGeneratedSources") {
                        doLast {
                            delete(
                                layout.buildDirectory.dir(".cxx"),
                                layout.buildDirectory.dir("intermediates/cxx"),
                                layout.buildDirectory.dir("intermediates/cmake"),
                                layout.buildDirectory.dir("intermediates/ndkBuild")
                            )
                        }
                    }
                    // Register a verification task
                    tasks.register("verifyNativeConfig") {
                        group = "verification"
                        description = "Verifies native build configuration"
                        doLast {
                            val cmakeFile = project.file("src/main/cpp/CMakeLists.txt")
                            if (cmakeFile.exists()) {
                                logger.lifecycle("✓ Native configuration active: CMakeLists.txt found")
                            } else {
                                logger.lifecycle("ℹ Native configuration skipped: No CMakeLists.txt")
                            }
                        }
                    }
                }
            }
        }
    }
}
