package dev.aurakai.auraframefx.oracle.drive.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.genesis.security.CryptographyManager;
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
public final class OracleDriveModule_Companion_ProvideGenesisCryptographyManagerFactory implements Factory<CryptographyManager> {
  private final Provider<Context> contextProvider;

  private OracleDriveModule_Companion_ProvideGenesisCryptographyManagerFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CryptographyManager get() {
    return provideGenesisCryptographyManager(contextProvider.get());
  }

  public static OracleDriveModule_Companion_ProvideGenesisCryptographyManagerFactory create(
      Provider<Context> contextProvider) {
    return new OracleDriveModule_Companion_ProvideGenesisCryptographyManagerFactory(contextProvider);
  }

  public static CryptographyManager provideGenesisCryptographyManager(Context context) {
    return Preconditions.checkNotNullFromProvides(OracleDriveModule.Companion.provideGenesisCryptographyManager(context));
  }
}
