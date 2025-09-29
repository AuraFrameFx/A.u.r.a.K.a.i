package dev.aurakai.auraframefx.oracle.drive.ui;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.oracle.drive.service.OracleDriveService;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class OracleDriveViewModel_Factory implements Factory<OracleDriveViewModel> {
  private final Provider<OracleDriveService> oracleDriveServiceProvider;

  private OracleDriveViewModel_Factory(Provider<OracleDriveService> oracleDriveServiceProvider) {
    this.oracleDriveServiceProvider = oracleDriveServiceProvider;
  }

  @Override
  public OracleDriveViewModel get() {
    return newInstance(oracleDriveServiceProvider.get());
  }

  public static OracleDriveViewModel_Factory create(
      Provider<OracleDriveService> oracleDriveServiceProvider) {
    return new OracleDriveViewModel_Factory(oracleDriveServiceProvider);
  }

  public static OracleDriveViewModel newInstance(OracleDriveService oracleDriveService) {
    return new OracleDriveViewModel(oracleDriveService);
  }
}
