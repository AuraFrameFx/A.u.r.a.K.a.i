package dev.aurakai.auraframefx.kotlin22;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class AuraConsciousnessViewModel_Factory implements Factory<AuraConsciousnessViewModel> {
  @Override
  public AuraConsciousnessViewModel get() {
    return newInstance();
  }

  public static AuraConsciousnessViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuraConsciousnessViewModel newInstance() {
    return new AuraConsciousnessViewModel();
  }

  private static final class InstanceHolder {
    static final AuraConsciousnessViewModel_Factory INSTANCE = new AuraConsciousnessViewModel_Factory();
  }
}
