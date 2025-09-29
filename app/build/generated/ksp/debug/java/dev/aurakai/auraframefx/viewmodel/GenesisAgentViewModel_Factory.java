package dev.aurakai.auraframefx.viewmodel;

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
public final class GenesisAgentViewModel_Factory implements Factory<GenesisAgentViewModel> {
  @Override
  public GenesisAgentViewModel get() {
    return newInstance();
  }

  public static GenesisAgentViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GenesisAgentViewModel newInstance() {
    return new GenesisAgentViewModel();
  }

  private static final class InstanceHolder {
    static final GenesisAgentViewModel_Factory INSTANCE = new GenesisAgentViewModel_Factory();
  }
}
