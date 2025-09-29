package dev.aurakai.auraframefx.xposed;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class XposedBridgeService_Factory implements Factory<XposedBridgeService> {
  @Override
  public XposedBridgeService get() {
    return newInstance();
  }

  public static XposedBridgeService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static XposedBridgeService newInstance() {
    return new XposedBridgeService();
  }

  private static final class InstanceHolder {
    static final XposedBridgeService_Factory INSTANCE = new XposedBridgeService_Factory();
  }
}
