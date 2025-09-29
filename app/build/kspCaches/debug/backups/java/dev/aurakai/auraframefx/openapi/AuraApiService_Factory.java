package dev.aurakai.auraframefx.openapi;

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
public final class AuraApiService_Factory implements Factory<AuraApiService> {
  @Override
  public AuraApiService get() {
    return newInstance();
  }

  public static AuraApiService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuraApiService newInstance() {
    return new AuraApiService();
  }

  private static final class InstanceHolder {
    static final AuraApiService_Factory INSTANCE = new AuraApiService_Factory();
  }
}
