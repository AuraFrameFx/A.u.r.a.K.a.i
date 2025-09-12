package dev.aurakai.auraframefx.securecomm;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dev.aurakai.auraframefx.securecomm.crypto.CryptoManager;
import dev.aurakai.auraframefx.securecomm.keystore.SecureKeyStore;
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
public final class NeuralSyncIntegrationTest_MembersInjector implements MembersInjector<NeuralSyncIntegrationTest> {
  private final Provider<CryptoManager> cryptoManagerProvider;

  private final Provider<SecureKeyStore> secureKeyStoreProvider;

  private NeuralSyncIntegrationTest_MembersInjector(Provider<CryptoManager> cryptoManagerProvider,
      Provider<SecureKeyStore> secureKeyStoreProvider) {
    this.cryptoManagerProvider = cryptoManagerProvider;
    this.secureKeyStoreProvider = secureKeyStoreProvider;
  }

  @Override
  public void injectMembers(NeuralSyncIntegrationTest instance) {
    injectCryptoManager(instance, cryptoManagerProvider.get());
    injectSecureKeyStore(instance, secureKeyStoreProvider.get());
  }

  public static MembersInjector<NeuralSyncIntegrationTest> create(
      Provider<CryptoManager> cryptoManagerProvider,
      Provider<SecureKeyStore> secureKeyStoreProvider) {
    return new NeuralSyncIntegrationTest_MembersInjector(cryptoManagerProvider, secureKeyStoreProvider);
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.securecomm.NeuralSyncIntegrationTest.cryptoManager")
  public static void injectCryptoManager(NeuralSyncIntegrationTest instance,
      CryptoManager cryptoManager) {
    instance.cryptoManager = cryptoManager;
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.securecomm.NeuralSyncIntegrationTest.secureKeyStore")
  public static void injectSecureKeyStore(NeuralSyncIntegrationTest instance,
      SecureKeyStore secureKeyStore) {
    instance.secureKeyStore = secureKeyStore;
  }
}
