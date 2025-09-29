package dev.aurakai.auraframefx.services;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dev.aurakai.auraframefx.oracle.drive.utils.SecureFileManager;
import javax.annotation.processing.Generated;

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
public final class AuraDriveService_MembersInjector implements MembersInjector<AuraDriveService> {
  private final Provider<SecureFileManager> secureFileManagerProvider;

  private AuraDriveService_MembersInjector(Provider<SecureFileManager> secureFileManagerProvider) {
    this.secureFileManagerProvider = secureFileManagerProvider;
  }

  @Override
  public void injectMembers(AuraDriveService instance) {
    injectSecureFileManager(instance, secureFileManagerProvider.get());
  }

  public static MembersInjector<AuraDriveService> create(
      Provider<SecureFileManager> secureFileManagerProvider) {
    return new AuraDriveService_MembersInjector(secureFileManagerProvider);
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.services.AuraDriveService.secureFileManager")
  public static void injectSecureFileManager(AuraDriveService instance,
      SecureFileManager secureFileManager) {
    instance.secureFileManager = secureFileManager;
  }
}
