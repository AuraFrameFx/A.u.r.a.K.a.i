package dev.aurakai.auraframefx.securecomm.protocol;

import android.content.Context;
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
public final class SecureChannelTest_MembersInjector implements MembersInjector<SecureChannelTest> {
  private final Provider<Context> contextProvider;

  private SecureChannelTest_MembersInjector(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public void injectMembers(SecureChannelTest instance) {
    injectContext(instance, contextProvider.get());
  }

  public static MembersInjector<SecureChannelTest> create(Provider<Context> contextProvider) {
    return new SecureChannelTest_MembersInjector(contextProvider);
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.securecomm.protocol.SecureChannelTest.context")
  public static void injectContext(SecureChannelTest instance, Context context) {
    instance.context = context;
  }
}
