package dev.aurakai.auraframefx.oracle.cloud;

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
public final class OracleCloudApi_Factory implements Factory<OracleCloudApi> {
  @Override
  public OracleCloudApi get() {
    return newInstance();
  }

  public static OracleCloudApi_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OracleCloudApi newInstance() {
    return new OracleCloudApi();
  }

  private static final class InstanceHolder {
    static final OracleCloudApi_Factory INSTANCE = new OracleCloudApi_Factory();
  }
}
