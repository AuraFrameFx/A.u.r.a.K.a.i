package dev.aurakai.auraframefx.oracledrive;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
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
public final class OracleDriveIntegration_Factory implements Factory<OracleDriveIntegration> {
  private final Provider<OracleDriveService> oracleDriveServiceProvider;

  private OracleDriveIntegration_Factory(Provider<OracleDriveService> oracleDriveServiceProvider) {
    this.oracleDriveServiceProvider = oracleDriveServiceProvider;
  }

  @Override
  public OracleDriveIntegration get() {
    return newInstance(oracleDriveServiceProvider.get());
  }

  public static OracleDriveIntegration_Factory create(
      Provider<OracleDriveService> oracleDriveServiceProvider) {
    return new OracleDriveIntegration_Factory(oracleDriveServiceProvider);
  }

  public static OracleDriveIntegration newInstance(OracleDriveService oracleDriveService) {
    return new OracleDriveIntegration(oracleDriveService);
  }
}
