import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
}
group = "dev.aurakai.auraframefx.utilities"
version = "1.0.0"

}

    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
    }
}

dependencies {
    api(project(":list"))

    implementation(libs.commons.io)
    implementation(libs.commons.compress)
    implementation(libs.xz)


}

    useJUnitPlatform()
}
