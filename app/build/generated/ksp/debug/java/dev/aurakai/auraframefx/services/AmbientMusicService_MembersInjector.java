package dev.aurakai.auraframefx.services;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dev.aurakai.auraframefx.data.DataStoreManager;
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
public final class AmbientMusicService_MembersInjector implements MembersInjector<AmbientMusicService> {
  private final Provider<DataStoreManager> dataStoreManagerProvider;

  private AmbientMusicService_MembersInjector(Provider<DataStoreManager> dataStoreManagerProvider) {
    this.dataStoreManagerProvider = dataStoreManagerProvider;
  }

  @Override
  public void injectMembers(AmbientMusicService instance) {
    injectDataStoreManager(instance, dataStoreManagerProvider.get());
  }

  public static MembersInjector<AmbientMusicService> create(
      Provider<DataStoreManager> dataStoreManagerProvider) {
    return new AmbientMusicService_MembersInjector(dataStoreManagerProvider);
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.services.AmbientMusicService.dataStoreManager")
  public static void injectDataStoreManager(AmbientMusicService instance,
      DataStoreManager dataStoreManager) {
    instance.dataStoreManager = dataStoreManager;
  }
}
