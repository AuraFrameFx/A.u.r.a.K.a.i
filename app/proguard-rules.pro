# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep class dev.aurakai.oracledrive.IAuraDriveService { *; }
-keep class dev.aurakai.oracledrive.IAuraDriveService$Stub { *; }
-keep class dev.aurakai.oracledrive.IAuraDriveServiceCallback { *; }

-keep class com.highcapable.yukihookapi.hook.xposed.parasitic.ParasiticMember { *; }
-keepclassmembers class * {
    @com.highcapable.yukihookapi.hook.param.Param *;
    @com.highcapable.yukihookapi.hook.param.ParamContext *;
    @com.highcapable.yukihookapi.hook.param.ParamResult *;
}

# Keep YukiHook entry classes
-keep class * extends com.highcapable.yukihookapi.hook.xposed.proxy.YukiHookXposedInitProxy { *; }
-keep class * extends com.highcapable.yukihookapi.hook.xposed.proxy.YukiHookXposedModuleProxy { *; }
-keep class * extends com.highcapable.yukihookapi.hook.xposed.proxy.YukiHookXposedModule { *; }

# Keep YukiHook's reflection and proxy classes
-keep class * extends com.highcapable.yukihookapi.hook.factory.ClassFactory { *; }
-keep class * extends com.highcapable.yukihookapi.hook.factory.ConstructorFactory { *; }
-keep class * extends com.highcapable.yukihookapi.hook.factory.MethodFactory { *; }
-keep class * extends com.highcapable.yukihookapi.hook.factory.FieldFactory { *; }


