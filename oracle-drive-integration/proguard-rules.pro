# ProGuard rules for Oracle Drive Integration module

-keepattributes SourceFile,LineNumberTable
-keepattributes *Annotation*

# Only keep classes annotated with @Keep (for reflection/serialization)
-keep @androidx.annotation.Keep class * { *; }
# Add specific API classes below if needed
# -keep class dev.aurakai.auraframefx.oracle.MyApiClass { public *; }
