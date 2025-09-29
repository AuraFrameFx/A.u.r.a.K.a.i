package dev.aurakai.auraframefx.oracledrive;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.oracledrive.api.OracleDriveApi;
import dev.aurakai.auraframefx.oracledrive.security.DriveSecurityManager;
import dev.aurakai.auraframefx.oracledrive.storage.CloudStorageProvider;
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
public final class OracleDriveManager_Factory implements Factory<OracleDriveManager> {
  private final Provider<OracleDriveApi> oracleDriveApiProvider;

  private final Provider<CloudStorageProvider> cloudStorageProvider;

  private final Provider<DriveSecurityManager> securityManagerProvider;

  private OracleDriveManager_Factory(Provider<OracleDriveApi> oracleDriveApiProvider,
      Provider<CloudStorageProvider> cloudStorageProvider,
      Provider<DriveSecurityManager> securityManagerProvider) {
    this.oracleDriveApiProvider = oracleDriveApiProvider;
    this.cloudStorageProvider = cloudStorageProvider;
    this.securityManagerProvider = securityManagerProvider;
  }

  @Override
  public OracleDriveManager get() {
    return newInstance(oracleDriveApiProvider.get(), cloudStorageProvider.get(), securityManagerProvider.get());
  }

  public static OracleDriveManager_Factory create(Provider<OracleDriveApi> oracleDriveApiProvider,
      Provider<CloudStorageProvider> cloudStorageProvider,
      Provider<DriveSecurityManager> securityManagerProvider) {
    return new OracleDriveManager_Factory(oracleDriveApiProvider, cloudStorageProvider, securityManagerProvider);
  }

  public static OracleDriveManager newInstance(OracleDriveApi oracleDriveApi,
      CloudStorageProvider cloudStorageProvider, DriveSecurityManager securityManager) {
    return new OracleDriveManager(oracleDriveApi, cloudStorageProvider, securityManager);
  }
}
