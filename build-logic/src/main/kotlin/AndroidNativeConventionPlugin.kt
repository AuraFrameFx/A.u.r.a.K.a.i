import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register

class AndroidNativeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply the base Android library plugin first
            pluginManager.apply("com.android.library")

            // Configure the Android library extension for native builds
            extensions.configure<LibraryExtension> {
                defaultConfig.consumerProguardFiles("consumer-rules.pro")

                // Specify the path to your CMakeLists.txt file
                externalNativeBuild {
                    cmake {
                        path = file("src/main/cpp/CMakeLists.txt")
                    }
                }
            }

            // Register a task to clean generated native build files
            tasks.register<Delete>("cleanNativeBuild") {
                group = "build"
                description = "Cleans the generated CMake build files."
                delete(
                    project.layout.buildDirectory.dir("intermediates/cmake"),
                    project.layout.buildDirectory.dir("generated/data_binding_base_class_source_out")
                )
            }

            // Ensure the clean task runs before a new build starts
            tasks.named("preBuild") {
                dependsOn("cleanNativeBuild")
            }
        }
    }
}
