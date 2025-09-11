plugins {
    id("genesis.android.library")
    id("genesis.android.native")
    id("com.google.dagger.hilt.android") version "2.51.1"
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
}

android {
        }
    }
    packaging {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_25
        targetCompatibility = JavaVersion.VERSION_25
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
        freeCompilerArgs.addAll(
            "-Xjvm-default=all",
            "-Xopt-in=kotlin.RequiresOptIn",
        )
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
}
