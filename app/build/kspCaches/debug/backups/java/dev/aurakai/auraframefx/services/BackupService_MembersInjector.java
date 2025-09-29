package dev.aurakai.auraframefx.services;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dev.aurakai.auraframefx.ai.memory.MemoryManager;
import dev.aurakai.auraframefx.data.DataStoreManager;
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
public final class BackupService_MembersInjector implements MembersInjector<BackupService> {
  private final Provider<DataStoreManager> dataStoreManagerProvider;

  private final Provider<SecureFileManager> secureFileManagerProvider;

  private final Provider<MemoryManager> memoryManagerProvider;

  private BackupService_MembersInjector(Provider<DataStoreManager> dataStoreManagerProvider,
      Provider<SecureFileManager> secureFileManagerProvider,
      Provider<MemoryManager> memoryManagerProvider) {
    this.dataStoreManagerProvider = dataStoreManagerProvider;
    this.secureFileManagerProvider = secureFileManagerProvider;
    this.memoryManagerProvider = memoryManagerProvider;
  }

  @Override
  public void injectMembers(BackupService instance) {
    injectDataStoreManager(instance, dataStoreManagerProvider.get());
    injectSecureFileManager(instance, secureFileManagerProvider.get());
    injectMemoryManager(instance, memoryManagerProvider.get());
  }

  public static MembersInjector<BackupService> create(
      Provider<DataStoreManager> dataStoreManagerProvider,
      Provider<SecureFileManager> secureFileManagerProvider,
      Provider<MemoryManager> memoryManagerProvider) {
    return new BackupService_MembersInjector(dataStoreManagerProvider, secureFileManagerProvider, memoryManagerProvider);
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.services.BackupService.dataStoreManager")
  public static void injectDataStoreManager(BackupService instance,
      DataStoreManager dataStoreManager) {
    instance.dataStoreManager = dataStoreManager;
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.services.BackupService.secureFileManager")
  public static void injectSecureFileManager(BackupService instance,
      SecureFileManager secureFileManager) {
    instance.secureFileManager = secureFileManager;
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.services.BackupService.memoryManager")
  public static void injectMemoryManager(BackupService instance, MemoryManager memoryManager) {
    instance.memoryManager = memoryManager;
  }
}
