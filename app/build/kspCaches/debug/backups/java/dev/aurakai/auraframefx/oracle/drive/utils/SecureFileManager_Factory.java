package dev.aurakai.auraframefx.oracle.drive.utils;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SecureFileManager_Factory implements Factory<SecureFileManager> {
  private final Provider<Context> contextProvider;

  private final Provider<EncryptionManager> encryptionManagerProvider;

  private SecureFileManager_Factory(Provider<Context> contextProvider,
      Provider<EncryptionManager> encryptionManagerProvider) {
    this.contextProvider = contextProvider;
    this.encryptionManagerProvider = encryptionManagerProvider;
  }

  @Override
  public SecureFileManager get() {
    return newInstance(contextProvider.get(), encryptionManagerProvider.get());
  }

  public static SecureFileManager_Factory create(Provider<Context> contextProvider,
      Provider<EncryptionManager> encryptionManagerProvider) {
    return new SecureFileManager_Factory(contextProvider, encryptionManagerProvider);
  }

  public static SecureFileManager newInstance(Context context,
      EncryptionManager encryptionManager) {
    return new SecureFileManager(context, encryptionManager);
  }
}
