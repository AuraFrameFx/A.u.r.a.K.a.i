package dev.aurakai.auraframefx.oracledrive;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.agents.AuraAgent;
import dev.aurakai.auraframefx.ai.agents.GenesisAgent;
import dev.aurakai.auraframefx.ai.agents.KaiAgent;
import dev.aurakai.auraframefx.oracle.drive.api.OracleDriveApi;
import dev.aurakai.auraframefx.security.SecurityContext;
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
public final class OracleDriveServiceImpl_Factory implements Factory<OracleDriveServiceImpl> {
  private final Provider<GenesisAgent> genesisAgentProvider;

  private final Provider<AuraAgent> auraAgentProvider;

  private final Provider<KaiAgent> kaiAgentProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<OracleDriveApi> oracleDriveApiProvider;

  private OracleDriveServiceImpl_Factory(Provider<GenesisAgent> genesisAgentProvider,
      Provider<AuraAgent> auraAgentProvider, Provider<KaiAgent> kaiAgentProvider,
      Provider<SecurityContext> securityContextProvider,
      Provider<OracleDriveApi> oracleDriveApiProvider) {
    this.genesisAgentProvider = genesisAgentProvider;
    this.auraAgentProvider = auraAgentProvider;
    this.kaiAgentProvider = kaiAgentProvider;
    this.securityContextProvider = securityContextProvider;
    this.oracleDriveApiProvider = oracleDriveApiProvider;
  }

  @Override
  public OracleDriveServiceImpl get() {
    return newInstance(genesisAgentProvider.get(), auraAgentProvider.get(), kaiAgentProvider.get(), securityContextProvider.get(), oracleDriveApiProvider.get());
  }

  public static OracleDriveServiceImpl_Factory create(Provider<GenesisAgent> genesisAgentProvider,
      Provider<AuraAgent> auraAgentProvider, Provider<KaiAgent> kaiAgentProvider,
      Provider<SecurityContext> securityContextProvider,
      Provider<OracleDriveApi> oracleDriveApiProvider) {
    return new OracleDriveServiceImpl_Factory(genesisAgentProvider, auraAgentProvider, kaiAgentProvider, securityContextProvider, oracleDriveApiProvider);
  }

  public static OracleDriveServiceImpl newInstance(GenesisAgent genesisAgent, AuraAgent auraAgent,
      KaiAgent kaiAgent, SecurityContext securityContext, OracleDriveApi oracleDriveApi) {
    return new OracleDriveServiceImpl(genesisAgent, auraAgent, kaiAgent, securityContext, oracleDriveApi);
  }
}
