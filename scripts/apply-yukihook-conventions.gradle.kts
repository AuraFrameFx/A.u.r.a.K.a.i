// Apply YukiHook conventions to all modules
subprojects {
    if (name == "build-logic" || name == "buildSrc") return@subprojects

    afterEvaluate {
        val isAndroidModule = plugins.hasPlugin("com.android.library") || plugins.hasPlugin("com.android.application")
        if (isAndroidModule) {
            // Add dependencies safely
            dependencies.apply {
                add("implementation", "com.highcapable.yukihookapi:api:1.3.1")
                add("implementation", files("${rootDir}/Libs/api-82.jar"))
                add("implementation", files("${rootDir}/Libs/api-82-sources.jar"))
                add("implementation", "androidx.core:core-ktx:1.17.0")
                add("testImplementation", "junit:junit:4.13.2")
                add("androidTestImplementation", "androidx.test.ext:junit:1.3.0")
                add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.7.0")
            }

            // Only log a message for Android config (do not attempt to configure extensions directly)
            logger.lifecycle("[YukiHook] Convention dependencies applied to $name. Please set compileSdk, minSdk, targetSdk, and other Android configs in each module's build.gradle.kts.")

            // Configure LSParanoid if present
            extensions.findByName("lsparanoid")?.let { ext ->
                ext.javaClass.getMethod("setSeed", Long::class.java).invoke(ext, 0x2A)
                ext.javaClass.getMethod("setIncludeAsSharedUuid", Boolean::class.java).invoke(ext, true)
            }

            // Configure KSP if present
            extensions.findByName("ksp")?.let { ext ->
                ext.javaClass.getMethod("arg", String::class.java, String::class.java)
                    .invoke(ext, "YUKIHOOK_PACKAGE_NAME", group.toString())
            }
        }
    }
}
