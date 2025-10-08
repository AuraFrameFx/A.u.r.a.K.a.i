# Consumer ProGuard rules for oracle-drive-integration

# Keep Oracle Drive Integration APIs
-keep public class dev.aurakai.auraframefx.oracle.** { 
    public *; 
}

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}
