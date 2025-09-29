package dev.aurakai.auraframefx.security;

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
public final class DefaultSecurityContext_Factory implements Factory<DefaultSecurityContext> {
  @Override
  public DefaultSecurityContext get() {
    return newInstance();
  }

  public static DefaultSecurityContext_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DefaultSecurityContext newInstance() {
    return new DefaultSecurityContext();
  }

  private static final class InstanceHolder {
    static final DefaultSecurityContext_Factory INSTANCE = new DefaultSecurityContext_Factory();
  }
}
