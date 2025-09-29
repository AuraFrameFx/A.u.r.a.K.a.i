package dev.aurakai.auraframefx.oracle;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class OracleDriveSandbox_Factory implements Factory<OracleDriveSandbox> {
  private final Provider<Context> contextProvider;

  private OracleDriveSandbox_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OracleDriveSandbox get() {
    return newInstance(contextProvider.get());
  }

  public static OracleDriveSandbox_Factory create(Provider<Context> contextProvider) {
    return new OracleDriveSandbox_Factory(contextProvider);
  }

  public static OracleDriveSandbox newInstance(Context context) {
    return new OracleDriveSandbox(context);
  }
}
