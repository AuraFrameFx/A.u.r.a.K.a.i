package dev.aurakai.auraframefx.security;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
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
public final class IntegrityMonitorService_MembersInjector implements MembersInjector<IntegrityMonitorService> {
  private final Provider<SecurityContext> securityContextProvider;

  private IntegrityMonitorService_MembersInjector(
      Provider<SecurityContext> securityContextProvider) {
    this.securityContextProvider = securityContextProvider;
  }

  @Override
  public void injectMembers(IntegrityMonitorService instance) {
    injectSecurityContext(instance, securityContextProvider.get());
  }

  public static MembersInjector<IntegrityMonitorService> create(
      Provider<SecurityContext> securityContextProvider) {
    return new IntegrityMonitorService_MembersInjector(securityContextProvider);
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.security.IntegrityMonitorService.securityContext")
  public static void injectSecurityContext(IntegrityMonitorService instance,
      SecurityContext securityContext) {
    instance.securityContext = securityContext;
  }
}
