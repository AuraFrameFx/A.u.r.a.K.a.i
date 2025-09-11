// AOSP-ReGenesis/build-logic/build.gradle.kts

plugins {
    `kotlin-dsl`
}


// Dependencies required for the convention plugins themselves.
dependencies {
}

gradlePlugin {
    plugins {
        }
        }
        }
    register("androidCompose") {
            id = "genesis.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidNative") {
            id = "genesis.android.native"
            implementationClass = "AndroidNativeConventionPlugin"
        }
    }
kotlin {
    jvmToolchain(23)
}