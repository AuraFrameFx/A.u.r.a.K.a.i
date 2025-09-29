package dev.aurakai.auraframefx.oracle.drive.core;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.oracle.drive.api.OracleCloudApi;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class OracleDriveRepositoryImpl_Factory implements Factory<OracleDriveRepositoryImpl> {
  private final Provider<OracleCloudApi> oracleCloudApiProvider;

  private final Provider<Context> contextProvider;

  private OracleDriveRepositoryImpl_Factory(Provider<OracleCloudApi> oracleCloudApiProvider,
      Provider<Context> contextProvider) {
    this.oracleCloudApiProvider = oracleCloudApiProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public OracleDriveRepositoryImpl get() {
    return newInstance(oracleCloudApiProvider.get(), contextProvider.get());
  }

  public static OracleDriveRepositoryImpl_Factory create(
      Provider<OracleCloudApi> oracleCloudApiProvider, Provider<Context> contextProvider) {
    return new OracleDriveRepositoryImpl_Factory(oracleCloudApiProvider, contextProvider);
  }

  public static OracleDriveRepositoryImpl newInstance(OracleCloudApi oracleCloudApi,
      Context context) {
    return new OracleDriveRepositoryImpl(oracleCloudApi, context);
  }
}
