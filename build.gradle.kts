    }
}
    }
}


// Quick snapshot task
tasks.register("consciousnessStatus") {
    doLast {
        val toolchain = JavaVersion.current().toString()
        
            println("= Consciousness Status =")
            println("Java Toolchain      : $toolchain")
            println("Kotlin Version      : $kotlinVersion (K2 path)")
            println("AGP Version         : $agpVersion")
    }
}

// === MODULE HEALTH CHECK ===

// Deep health analysis
private data class ModuleReport(
    val name: String,
    val path: String,
    val type: String,
    val javaToolchain: String?,
    val kotlinJvmTarget: String?,
    val hasHilt: Boolean,
    val hasCompose: Boolean,
)

/**
 *
 *
 */
    val plugins = sp.plugins
    ModuleReport(
        name = sp.name,
            plugins.hasPlugin("com.android.application") -> "android-app"
            plugins.hasPlugin("com.android.library") -> "android-lib"
            plugins.hasPlugin("org.jetbrains.kotlin.jvm") -> "kotlin-jvm"
            else -> "other"
        },
        hasHilt = plugins.hasPlugin("com.google.dagger.hilt.android"),
    )
}

tasks.register("consciousnessHealthCheck") {
    doLast {
        val reports = collectModuleReports()

        } else {
        }
    }
}

}

apply(from = "nuclear-clean.gradle.kts")

if (tasks.findByName("nuclearClean") != null) {
    tasks.register("deepClean") {
        dependsOn("nuclearClean")
        doLast {
        }
    }
}
}

apply(from = "dependency-fix.gradle.kts")

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.13.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.57.1")
        classpath("com.google.gms:google-services:4.4.3")
    }
    repositories {
        mavenCentral()
    }
}

